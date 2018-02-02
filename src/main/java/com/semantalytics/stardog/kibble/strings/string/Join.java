
package com.semantalytics.stardog.kibble.strings.string;

import com.complexible.common.rdf.model.Values;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import com.google.common.base.Joiner;
import org.openrdf.model.Value;

import java.util.Arrays;
import java.util.List;
import com.google.common.collect.Range;

import static java.util.stream.Collectors.toList;

public final class JoinWith extends AbstractFunction implements StringFunction {

    protected Join() {
        super(Range.atLeast(1), StringVocabulary.join.stringValue());
    }

    private Join(final Join join) {
        super(join);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
      assertStringLiteral(values[0]);
      final String separator = values[0].stringValue();
      final List<String> pieces = Arrays.stream(values).skip(1).map(v -> v.stringValue()).collect(toList());
      return Values.literal(Joiner.on(separator).join(pieces));
      //TODO use StringUtils
    }

    @Override
    public Join copy() {
        return new Join(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.join.name();
    }
}