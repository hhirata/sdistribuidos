package br.ufmt.dados;


import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;



public class TrataXMLDados {
	
	public DadosArquivo le(File req) throws JAXBException, IOException{

		JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		JAXBElement<Resposta> unmarshalledObject = 
				(JAXBElement<Resposta>)unmarshaller.unmarshal(req);
		Resposta rqObj = unmarshalledObject.getValue();
		DadosArquivo arq = rqObj.getDadosArquivo();
		return arq;

	}

	public TrataXMLDados() {
		// TODO Auto-generated constructor stub
	}

}
