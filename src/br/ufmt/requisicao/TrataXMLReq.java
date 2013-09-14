package br.ufmt.requisicao;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import br.ufmt.arquivoReq.RandomAccessFileEx;

public class TrataXMLReq {

	public byte[] parteArquivo(String req, String caminho) throws JAXBException, IOException{

		JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		JAXBElement<SolicitaArquivo> unmarshalledObject = 
				(JAXBElement<SolicitaArquivo>)unmarshaller.unmarshal(new ByteArrayInputStream(req.getBytes()));
		SolicitaArquivo rqObj = unmarshalledObject.getValue();
		RequisitaArquivo arq = rqObj.getRequisita();
		return new RandomAccessFileEx().readFromFile(caminho+arq.getNome(),arq.getPosicao(),arq.getTamanho());

	}

	public String criarXmlReq(RequisitaArquivo req) throws JAXBException{
		ObjectFactory obj = new ObjectFactory();
		SolicitaArquivo sl = new SolicitaArquivo();
		sl.setRequisita(req);
		StringWriter saida = new StringWriter();
		JAXBContext context = JAXBContext.newInstance("br.ufmt.principal");
		JAXBElement<SolicitaArquivo> element = obj.createSolicitacao(sl);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty("jaxb.formatted.output",Boolean.TRUE);
		marshaller.marshal(element, saida);
		
		return saida.toString();

	}


}
