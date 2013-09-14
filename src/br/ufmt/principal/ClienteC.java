package br.ufmt.principal;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.net.Socket;
import java.util.ArrayList;

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
			
			
			
			//Montando o arquivo em partes na memória via localhost
			
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
			
			
			//multiplas partes em disco

/*			int tamanho=177664;
			int cont =0;
			int qtd=1024;
			RandomAccessFileEx hand2 = new RandomAccessFileEx();
			byte[]bts = new byte[tamanho];
			RandomAccessFile arquivo = new RandomAccessFile("D:\\Downloads\\estrutura.doc", "rw");
			while(tamanho > 0){
				if(qtd>tamanho){
					qtd=tamanho;

				}
				Socket s = new Socket(servidorString, porta);
				ObjectOutputStream oo = new ObjectOutputStream(s.getOutputStream());
				RequisitaArquivo rq = new RequisitaArquivo();
				rq.setNome("estrutura.doc");
				rq.setPosicao(cont);
				rq.setTamanho(qtd);

				String string = new TrataXML().criarXmlReq(rq);
				oo.writeObject(string);

				DataInputStream dt = new DataInputStream(s.getInputStream());

				int i = dt.readInt();
				byte []b = new byte[i];
				dt.readFully(b,0,b.length);
				
				RandomAccessFileEx hand = new RandomAccessFileEx();
				hand.writeToFile("D:\\Downloads\\11.rar", b, cont);
				
				new Thread(new ManipulaArquivo("D:\\Downloads\\estrutura.doc", b, cont,arquivo)).start();;
				cont= cont+qtd;
				tamanho= tamanho- qtd;

				s.close();
			}
			arquivo.close();*/
			ArrayList<String> ips = new ArrayList<>();
			ips.add("192.168.1.3");
			ips.add("192.168.1.4");
			int max = ips.size();
			int contador=0;
			int tamanho=29918871;
			int cont =0;
			int qtd=32768;
			System.out.println(max);
			RandomAccessFileEx hand2 = new RandomAccessFileEx();
			byte[]bts = new byte[tamanho];
			RandomAccessFile arquivo = new RandomAccessFile("D:\\Downloads\\Data.pdf", "rw");
			while(tamanho > 0){
				if(qtd>tamanho){
					qtd=tamanho;

				}
				
				String end= ips.get(contador);
				Socket s = new Socket(end, porta);
				
				ObjectOutputStream oo = new ObjectOutputStream(s.getOutputStream());
				RequisitaArquivo rq = new RequisitaArquivo();
				rq.setNome("Data.pdf");
				rq.setPosicao(cont);
				rq.setTamanho(qtd);

				String string = new TrataXML().criarXmlReq(rq);
				oo.writeObject(string);

				DataInputStream dt = new DataInputStream(s.getInputStream());

				int i = dt.readInt();
				byte []b = new byte[i];
				dt.readFully(b,0,b.length);
				System.out.println("Recebendo de "+end);
/*				RandomAccessFileEx hand = new RandomAccessFileEx();
				hand.writeToFile("D:\\Downloads\\11.rar", b, cont);*/
				if(contador+1 <max){
					contador++;
				}
				else{
					contador=0;
					
				}
				new Thread(new ManipulaArquivo("D:\\Downloads\\Data.pdf", b, cont,arquivo)).start();;
				cont= cont+qtd;
				tamanho= tamanho- qtd;

				s.close();
			}
			arquivo.close();
			
		}catch (Exception e){
			e.printStackTrace();
		}

	}

}
