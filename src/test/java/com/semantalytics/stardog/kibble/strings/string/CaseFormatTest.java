package com.semantalytics.stardog.kibble.strings.string;

import com.semantalytics.stardog.kibble.AbstractStardogTest;
import org.junit.*;
import org.openrdf.query.BindingSet;
import org.openrdf.query.TupleQueryResult;

import static org.junit.Assert.*;

public class CaseFormatTest extends AbstractStardogTest {
 

    @Test
    public void testLowerCamelToUpperUnderscoreByExample() throws Exception {
       
            final String aQuery = "prefix ss: <" + StringVocabulary.NAMESPACE + "> " +
                    "select ?caseFormat where { bind(ss:caseFormat(\"caseFormat\", \"CASE_FORMAT\", \"stardogUnion\") as ?caseFormat) }";

            final TupleQueryResult aResult = connection.select(aQuery).execute();

       
                assertTrue("Should have a result", aResult.hasNext());

                final String aValue = aResult.next().getValue("caseFormat").stringValue();

                assertEquals("STARDOG_UNION", aValue);

                assertFalse("Should have no more results", aResult.hasNext());
            
    }

    @Test
    public void testLowerCamelToLowerUnderscoreByExample() throws Exception {
     
            final String aQuery = "prefix ss: <" + StringVocabulary.NAMESPACE + "> " +
                    "select ?caseFormat where { bind(ss:caseFormat(\"caseFormat\", \"case_format\", \"stardogUnion\") as ?caseFormat) }";

            final TupleQueryResult aResult = connection.select(aQuery).execute();

   
                assertTrue("Should have a result", aResult.hasNext());

                final String aValue = aResult.next().getValue("caseFormat").stringValue();

                assertEquals("stardog_union", aValue);

                assertFalse("Should have no more results", aResult.hasNext());
            
    }

    @Test
    public void testLowerCamelToLowerHyphenByExample() throws Exception {
       

            final String aQuery = "prefix ss: <" + StringVocabulary.NAMESPACE + "> " +
                    "select ?caseFormat where { bind(ss:caseFormat(\"caseFormat\", \"case-format\", \"stardogUnion\") as ?caseFormat) }";

            final TupleQueryResult aResult = connection.select(aQuery).execute();

           
                assertTrue("Should have a result", aResult.hasNext());

                final String aValue = aResult.next().getValue("caseFormat").stringValue();

                assertEquals("stardog-union", aValue);

                assertFalse("Should have no more results", aResult.hasNext());
         
    }

    @Test
    public void testLowerCamelToUpperCamelByExample() throws Exception {
    

            final String aQuery = "prefix ss: <" + StringVocabulary.NAMESPACE + "> " +
                    "select ?caseFormat where { bind(ss:caseFormat(\"caseFormat\", \"CaseFormat\", \"stardogUnion\") as ?caseFormat) }";

            final TupleQueryResult aResult = connection.select(aQuery).execute();

      
                assertTrue("Should have a result", aResult.hasNext());

                final String aValue = aResult.next().getValue("caseFormat").stringValue();

                assertEquals("StardogUnion", aValue);

                assertFalse("Should have no more results", aResult.hasNext());
 
    }

    @Test
    public void testTooManyArgs() throws Exception {

      
            final String aQuery = "prefix ss: <" + StringVocabulary.NAMESPACE + "> " +
                    "select ?caseFormat where { bind(ss:caseFormat(\"one\", \"two\", \"three\", \"four\") as ?caseFormat) }";

            final TupleQueryResult aResult = connection.select(aQuery).execute();
        
                // there should be a result because implicit in the query is the singleton set, so because the bind
                // should fail due to the value error, we expect a single empty binding
                assertTrue("Should have a result", aResult.hasNext());

                final BindingSet aBindingSet = aResult.next();

                assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());

                assertFalse("Should have no more results", aResult.hasNext());
           
    }



    @Test
    public void testWrongType() throws Exception {
    

            final String aQuery = "prefix ss: <" + StringVocabulary.NAMESPACE + "> " +
                    "select ?caseFormat where { bind(ss:caseFormat(7, 8, 9) as ?caseFormat) }";

            final TupleQueryResult aResult = connection.select(aQuery).execute();
        
                // there should be a result because implicit in the query is the singleton set, so because the bind
                // should fail due to the value error, we expect a single empty binding
                assertTrue("Should have a result", aResult.hasNext());

                final BindingSet aBindingSet = aResult.next();

                assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());

                assertFalse("Should have no more results", aResult.hasNext());
            
    }
}
