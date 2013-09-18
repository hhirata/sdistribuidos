package br.ufmt.publica;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;



public class TrataXmlPlub {

	public TrataXmlPlub() {
		// TODO Auto-generated constructor stub
	}

	public String criaXmlPubl(Publica pb) throws JAXBException{
		ObjectFactory obj = new ObjectFactory();
		SolicitaPublicacao sl = new SolicitaPublicacao();
		sl.setPublica(pb);
		StringWriter saida = new StringWriter();
		JAXBContext context = JAXBContext.newInstance("br.ufmt.publica");
		JAXBElement<SolicitaPublicacao> element = obj.createSolicitacao(sl);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty("jaxb.formatted.output",Boolean.TRUE);
		marshaller.marshal(element, saida);
		
		return saida.toString();
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
