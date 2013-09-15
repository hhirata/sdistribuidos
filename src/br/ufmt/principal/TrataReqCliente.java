package br.ufmt.principal;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.xml.bind.JAXBException;

import br.ufmt.requisicao.TrataXMLReq;
import br.ufmt.xml.ValidaXML;

public class TrataReqCliente implements Runnable{

	private Socket cliente;
	private String caminho;
	public void run() {
		try {
			ObjectInputStream oi = new ObjectInputStream(cliente.getInputStream());
			Object obj = oi.readObject();
			
			if(obj instanceof String){
				boolean b = new ValidaXML().validaReq((String)obj);
				if(b){
					try {
						System.out.println("verdade");
						byte[] parte = new TrataXMLReq().parteArquivo((String)obj,caminho);
						DataOutputStream saida = new DataOutputStream(cliente.getOutputStream());
						/*					Conteudo ctd = new Conteudo();
						ctd.setCtd(parte);
						System.out.println(ctd.getCtd().hashCode());
						saida.writeObject(ctd);*/
						saida.writeInt(parte.length);
						saida.write(parte);
						cliente.close();
					} catch (JAXBException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();

					} 
				}else{
					System.out.println("falso");
					DataOutputStream saida = new DataOutputStream(cliente.getOutputStream());
					saida.writeInt(-1);
					cliente.close();
				}
			}
		
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		}
		public String getCaminho() {
			return caminho;
		}
		public void setCaminho(String caminnho) {
			this.caminho = caminnho;
		}
		public Socket getCliente() {
			return cliente;
		}
		public void setCliente(Socket cliente) {
			this.cliente = cliente;
		}
		public TrataReqCliente(Socket cliente,String caminho) {

			this.cliente = cliente;
			this.caminho=caminho;
		}


	}
