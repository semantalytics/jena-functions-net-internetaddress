package com.semantalytics.stardog.kibble.net.internetaddress;

import com.complexible.common.rdf.model.Values;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.Function;
import com.complexible.stardog.plan.filter.functions.UserDefinedFunction;
import com.google.common.base.Joiner;
import com.google.common.primitives.Longs;
import org.openrdf.model.Value;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static com.complexible.common.rdf.model.Values.literal;

/**
 * Given a numeric IPv4 network address in network byte order, returns the dotted-quad
 * string representation of the address as a nonbinary string in the connection character set
 */
public class InternetNumberToAddress extends AbstractFunction implements UserDefinedFunction {

    private static final long FIRST_OCTET_BASE = 16777216;
    private static final long SECOND_OCTET_BASE = 65536;
    private static final long THIRD_OCTET_BASE = 256;
    private static final long FOURTH_OCTET_BASE = 1;

    public InternetNumberToAddress() {
        super(1, InternetAddressVocabulary.inetNumberToAddress.stringValue());
    }

    private InternetNumberToAddress(InternetNumberToAddress internetNumberToAddress) {
        super(internetNumberToAddress);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final long ipNumber = assertNumericLiteral(values[0]).longValue();

        try {
            return literal(InetAddress.getByAddress(Longs.toByteArray(ipNumber)).toString());
        } catch(UnknownHostException e) {
            throw new ExpressionEvaluationException(e);
        }



        //TODO use Guava InetNumber
//        final long firstOctet = ( ipNumber / FIRST_OCTET_BASE ) % 256;
//        final long secondOctet = ( ipNumber / SECOND_OCTET_BASE ) % 256;
//        final long thirdOctet = ( ipNumber / THIRD_OCTET_BASE ) % 256;
//        final long fourthOctet = ( ipNumber / FOURTH_OCTET_BASE ) % 256;
//
//        return Values.literal(Joiner.on('.').join(firstOctet, secondOctet, thirdOctet, fourthOctet));
    }

    @Override
    public Function copy() {
        return new InternetNumberToAddress(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return InternetAddressVocabulary.inetNumberToAddress.name();
    }
}
