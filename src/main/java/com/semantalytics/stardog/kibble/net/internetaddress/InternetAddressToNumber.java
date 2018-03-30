package com.semantalytics.stardog.kibble.net.internetaddress;

import com.complexible.common.rdf.model.Values;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.Function;
import com.complexible.stardog.plan.filter.functions.UserDefinedFunction;
import com.google.common.base.Splitter;
import com.google.common.net.InetAddresses;
import com.google.common.primitives.UnsignedInts;
import org.openrdf.model.Value;

import java.util.List;

import static com.complexible.common.rdf.model.Values.literal;

/**
 * Given the dotted-quad representation of an IPv4 network address as a string, returns an
 * integer that represents the numeric value of the address in network byte order (big endian)
 */
public class InternetAddressToNumber extends AbstractFunction implements UserDefinedFunction {
    //TODO user Guava Ints
    private static final long FIRST_OCTET_BASE = 16777216;
    private static final long SECOND_OCTET_BASE = 65536;
    private static final long THIRD_OCTET_BASE = 256;
    private static final long FOURTH_OCTET_BASE = 1;

    public InternetAddressToNumber() {
        super(1, InternetAddressVocabulary.inetAddressToNumber.stringValue());
    }

    private InternetAddressToNumber(final InternetAddressToNumber internetAddressToNumber) {
        super(internetAddressToNumber);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String ip = assertStringLiteral(values[0]).stringValue();

        return literal(UnsignedInts.toLong(InetAddresses.coerceToInteger(InetAddresses.forString(ip))));
    }

    @Override
    public Function copy() {
        return new InternetAddressToNumber(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return InternetAddressVocabulary.inetAddressToNumber.name();
    }

}
