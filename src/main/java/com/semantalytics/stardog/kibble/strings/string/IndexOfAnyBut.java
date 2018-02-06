package com.semantalytics.stardog.kibble.strings.string;

import com.complexible.common.rdf.model.Values;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

public final class IndexOfAnyBut extends AbstractFunction implements StringFunction {

    protected IndexOfAnyCharBut() {
        super(1, StringVocabulary.indexOfAnyCharBut.stringValue());
    }

    private IndexOfAnyCharBut(final IndexOfAnyCharBut indexOfAnyCharBut) {
        super(indexOfAnyCharBut);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringValue();
        final String searchChars = assertStringLiteral(values[1]).stringValue();



        //TODO handle multiple searchchars

        return Values.literal(StringUtils.indexOfAnyCharBut(string, searchChars));
    }

    @Override
    public IndexOfAnyCharBut copy() {
        return new IndexOfAnyCharBut(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.indexOfAnyCharBut.name();
    }
}
