package br.ufmt.principal;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.bind.JAXBException;

import br.ufmt.dados.DadosArquivo;
import br.ufmt.dados.TrataXMLDados;

public class novo {

	public novo() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File f = new File("C:\\Users\\Henrique Hirata\\Desktop\\Dados.xml");
		try {
			DadosArquivo d = new TrataXMLDados().le(f);
			ArrayList<String> a = (ArrayList<String>) d.getIp();
			for(String s : a)
			System.out.println(s);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
