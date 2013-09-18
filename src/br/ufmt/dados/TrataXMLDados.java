package br.ufmt.dados;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;





public class TrataXMLDados {
	
	
	public String criarXmlDados(DadosArquivo req) throws JAXBException{
		ObjectFactory obj = new ObjectFactory();
		Resposta res = new Resposta();
		res.setDadosArquivo(req);
		StringWriter saida = new StringWriter();
		JAXBContext context = JAXBContext.newInstance("br.ufmt.dados");
		JAXBElement<Resposta> element = obj.createResposta(res);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty("jaxb.formatted.output",Boolean.TRUE);
		marshaller.marshal(element, saida);
		
		return saida.toString();

	}
	
	public DadosArquivo le(File req) throws JAXBException, IOException{

		JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		JAXBElement<Resposta> unmarshalledObject = 
				(JAXBElement<Resposta>)unmarshaller.unmarshal(req);
		Resposta rqObj = unmarshalledObject.getValue();
		DadosArquivo arq = rqObj.getDadosArquivo();
		return arq;

	}
	public DadosArquivo le(String req) throws JAXBException, IOException{

		JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		JAXBElement<Resposta> unmarshalledObject = 
				(JAXBElement<Resposta>)unmarshaller.unmarshal(new ByteArrayInputStream(req.getBytes()));
		Resposta rqObj = unmarshalledObject.getValue();
		DadosArquivo arq = rqObj.getDadosArquivo();
		return arq;

	}


	public TrataXMLDados() {
		// TODO Auto-generated constructor stub
	}

}
