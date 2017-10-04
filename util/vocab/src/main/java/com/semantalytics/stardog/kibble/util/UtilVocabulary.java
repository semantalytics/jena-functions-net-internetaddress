package com.semantalytics.stardog.kibble.util;

import com.complexible.common.rdf.model.StardogValueFactory;
import org.openrdf.model.IRI;

public enum UtilVocabulary {

    bindPrev,
    fromSpokenTime,
    user,
    databaseName;

    public static final String NAMESPACE = "http://semantalytics.com/2017/09/ns/stardog/kibble/util/";
    public final IRI iri;

    UtilVocabulary() {
        iri = StardogValueFactory.instance().createIRI(NAMESPACE, name());
    }

    public String stringValue() {
        return iri.stringValue();
    }
}
