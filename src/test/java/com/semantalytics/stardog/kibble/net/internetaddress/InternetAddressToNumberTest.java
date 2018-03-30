package com.semantalytics.stardog.kibble.net.internetaddress;

import com.semantalytics.stardog.kibble.AbstractStardogTest;
import org.junit.Test;
import org.openrdf.query.TupleQueryResult;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class InternetAddressToNumberTest extends AbstractStardogTest {

    @Test
    public void testInetAddressToNumber() {

            final String aQuery = "prefix util: <" + InternetAddressVocabulary.NAMESPACE + "> " +
                    "select ?result where { bind(util:inetAddressToNumber(\"192.168.0.1\") as ?result) }";

            try (final TupleQueryResult aResult = connection.select(aQuery).execute()) {

                assertTrue("Should have a result", aResult.hasNext());

                final long aValue = Long.parseLong(aResult.next().getValue("result").stringValue());

                assertEquals(3232235521L, aValue);

                assertFalse("Should have no more results", aResult.hasNext());
            } 
    }
}
