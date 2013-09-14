package br.ufmt.principal;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClienteC implements Runnable {

	private String servidorString;
	private int porta;
	public String getServidorString() {
		return servidorString;
	}
	public void setServidorString(String servidorString) {
		this.servidorString = servidorString;
	}
	public int getPorta() {
		return porta;
	}
	public void setPorta(int porta) {
		this.porta = porta;
	}
	public ClienteC(String servidorString, int porta) {
		this.servidorString = servidorString;
		this.porta = porta;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			
			//Solicitando e enviando um arquivo pequeno 
			/* Socket s = new Socket(servidorString, porta);
        ObjectOutputStream oo = new ObjectOutputStream(s.getOutputStream());

        RequisitaArquivo rq = new RequisitaArquivo();
        rq.setNome("programa2");
        rq.setPosicao(0);
        rq.setTamanho(441);

        String string = new TrataXML().criarXmlReq(rq);
        oo.writeObject(string);

     //   ObjectInputStream oi = new ObjectInputStream(s.getInputStream());

       DataInputStream dt = new DataInputStream(s.getInputStream());

       int i = dt.readInt();
       byte []b = new byte[i];
       dt.readFully(b, 0,b.length);
       System.out.println(b);

       RandomAccessFileEx.writeToFile("D:\\Downloads\\programa2",b ,0);



        s.close();*/
			
			
			
			//Montando o arquivo em partes via localhost
			
/*			int tamanho=84398520;
			int cont =0;
			int qtd=8192;
			byte[] fil = new byte[tamanho];
			
			while(tamanho > 0){
				if(qtd>tamanho){
					qtd=tamanho;

				}
				Socket s = new Socket(servidorString, porta);
				ObjectOutputStream oo = new ObjectOutputStream(s.getOutputStream());
				RequisitaArquivo rq = new RequisitaArquivo();
				rq.setNome("NS330.rar");
				rq.setPosicao(cont);
				rq.setTamanho(qtd);

				String string = new TrataXML().criarXmlReq(rq);
				oo.writeObject(string);

				DataInputStream dt = new DataInputStream(s.getInputStream());

				int i = dt.readInt();
				//byte []b = new byte[i];
				dt.readFully(fil,cont,i);
				
				cont= cont+qtd;
				tamanho= tamanho- qtd;

				s.close();

			}
			 RandomAccessFileEx hand = new RandomAccessFileEx();
			 hand.writeToFile("D:\\Downloads\\NS330.rar",fil ,0);*/

		}catch (Exception e){
			e.printStackTrace();
		}

	}

}
