//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.09.14 at 03:16:55 PM AMT 
//


package br.ufmt.busca;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the br.ufmt.busca package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Solicitacao_QNAME = new QName("", "solicitacao");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: br.ufmt.busca
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SolicitaArquivoBusca }
     * 
     */
    public SolicitaArquivoBusca createSolicitaArquivoBusca() {
        return new SolicitaArquivoBusca();
    }

    /**
     * Create an instance of {@link Busca }
     * 
     */
    public Busca createBusca() {
        return new Busca();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SolicitaArquivoBusca }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "solicitacao")
    public JAXBElement<SolicitaArquivoBusca> createSolicitacao(SolicitaArquivoBusca value) {
        return new JAXBElement<SolicitaArquivoBusca>(_Solicitacao_QNAME, SolicitaArquivoBusca.class, null, value);
    }

}
