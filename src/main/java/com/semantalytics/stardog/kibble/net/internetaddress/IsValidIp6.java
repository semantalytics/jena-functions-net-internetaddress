package com.semantalytics.stardog.kibble.net.internetaddress;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.Function;
import com.complexible.stardog.plan.filter.functions.UserDefinedFunction;
import org.apache.commons.validator.routines.InetAddressValidator;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.literal;

public class IsValidIp6 extends AbstractFunction implements UserDefinedFunction {

    public IsValidIp6() {
        super(1, InternetAddressVocabulary.isValidIp6.stringValue());
    }

    private IsValidIp6(final IsValidIp6 isValidIp6) {
        super(isValidIp6);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String ip = assertStringLiteral(values[0]).stringValue();

        return literal(InetAddressValidator.getInstance().isValidInet6Address(ip));
    }

    @Override
    public Function copy() {
        return new IsValidIp6(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return InternetAddressVocabulary.isValidIp6.name();
    }

}
