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
        Socket s = new Socket(servidorString, porta);
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
     


        s.close();
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}

}
