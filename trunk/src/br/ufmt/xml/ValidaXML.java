package br.ufmt.xml;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;



public class ValidaXML {

	public ValidaXML() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean validaReq(String xml){
		
		 DocumentBuilderFactory dbf =
			      DocumentBuilderFactory.newInstance();
			    dbf.setNamespaceAware(true);
			    try {
			        DocumentBuilder parser = dbf.newDocumentBuilder();
			         Document document = parser.parse(new InputSource(new StringReader(xml)));
			        SchemaFactory factory = SchemaFactory
			        .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

			        Schema schema = null;
			        schema = factory.newSchema(new File("src/br/ufmt/xml/Solicita1.xsd"));
			        Validator validator = schema.newValidator();
			        validator.validate(new DOMSource(document));
			        return true;
			      } catch (SAXException e) {
			      //  e.printStackTrace();
			        return false;
			      } catch (IllegalArgumentException e) {
			       // e.printStackTrace();
			        return false;
			      } catch (IOException e) {
			       // e.printStackTrace();
			        return false;
			      } catch (ParserConfigurationException e) {
			       // e.printStackTrace();
			        return false;
			      }
		
	}
	
	public static void main(String[] args) {
		
		ValidaXML vl = new ValidaXML();
		boolean b = vl.validaReq("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><solicitacao><requisita><nome>Data.pdf</nome><posicao>2392064</posicao><tamanho>32768</tamanho></requisita></solicitacao>");
		if (b){
			System.out.println("verdade");
		}
		else{
			System.out.println("falso");
		}
	}

}
