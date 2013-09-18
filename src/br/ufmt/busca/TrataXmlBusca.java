package br.ufmt.busca;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;





public class TrataXmlBusca {

	public TrataXmlBusca() {
		// TODO Auto-generated constructor stub
	}
	
	public String criarXml(Busca b) throws JAXBException{
		ObjectFactory obj = new ObjectFactory();
		SolicitaArquivoBusca sl = new SolicitaArquivoBusca();
		sl.setBusca(b);
		StringWriter saida = new StringWriter();
		JAXBContext context = JAXBContext.newInstance("br.ufmt.busca");
		JAXBElement<SolicitaArquivoBusca> element = obj.createSolicitacao(sl);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty("jaxb.formatted.output",Boolean.TRUE);
		marshaller.marshal(element, saida);
		
		return saida.toString();

	}
	
	public String retornaNome(String req) throws JAXBException{
		JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		JAXBElement<SolicitaArquivoBusca> unmarshalledObject = 
				(JAXBElement<SolicitaArquivoBusca>)unmarshaller.unmarshal(new ByteArrayInputStream(req.getBytes()));
		SolicitaArquivoBusca rqObj = unmarshalledObject.getValue();
		Busca b = rqObj.getBusca();		
		return b.getNome();

	}

}
