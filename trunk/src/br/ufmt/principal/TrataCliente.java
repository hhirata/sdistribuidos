package br.ufmt.principal;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;

import javax.xml.bind.JAXBException;

import br.ufmt.banco.Consultas;
import br.ufmt.busca.TrataXmlBusca;
import br.ufmt.dados.DadosArquivo;
import br.ufmt.dados.TrataXMLDados;
import br.ufmt.publica.Publica;
import br.ufmt.publica.TrataXmlPlub;
import br.ufmt.xml.ValidaXML;

public class TrataCliente implements Runnable {
	private Socket cliente;
	public TrataCliente() {
		// TODO Auto-generated constructor stub
	}
	
	

	public Socket getCliente() {
		return cliente;
	}



	public void setCliente(Socket cliente) {
		this.cliente = cliente;
	}



	public TrataCliente(Socket cliente) {
		super();
		this.cliente = cliente;
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		ObjectInputStream oi;
		try {
			oi = new ObjectInputStream(cliente.getInputStream());
			Object obj = oi.readObject();
			if(obj instanceof String){
				if(new ValidaXML().validaBusca((String)obj)){
					String nome = new TrataXmlBusca().retornaNome((String)obj);
					DadosArquivo dados = new Consultas().buscaArquivo(nome);
					System.out.println(dados.getMd5());
					String dds = new TrataXMLDados().criarXmlDados(dados);
					ObjectOutputStream ob = new ObjectOutputStream(cliente.getOutputStream());
			        ob.writeObject(dds);
					
				}else{
					if(new ValidaXML().validaPublica((String)obj)){
						Publica pb = new TrataXmlPlub().Dados((String)obj);
						Consultas cons= new Consultas();
						cons.insereArquivo(pb);
						 
						
					}
				}
				
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
