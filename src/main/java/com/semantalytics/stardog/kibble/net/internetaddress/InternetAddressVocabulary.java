package com.semantalytics.stardog.kibble.net.internetaddress;

import com.complexible.common.rdf.model.StardogValueFactory;
import org.openrdf.model.IRI;

public enum InternetAddressVocabulary {

    inetAddressToNumber,
    inetNumberToAddress;

    public static final String NAMESPACE = "http://semantalytics.com/2017/09/ns/stardog/kibble/net/";
    public final IRI iri;

    InternetAddressVocabulary() {
        iri = StardogValueFactory.instance().createIRI(NAMESPACE, name());
    }

    public String stringValue() {
        return iri.stringValue();
    }
}
