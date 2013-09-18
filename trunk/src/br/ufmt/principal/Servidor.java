package br.ufmt.principal;

import java.io.IOException;
import java.net.ServerSocket;

public class Servidor implements Runnable{
	
	ServerSocket servidor;
	public Servidor() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		try {
			new Servidor(1023);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				// caminho = new String("D:\\");
				new Thread(new TrataCliente(servidor.accept())).start();;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public Servidor(int porta) throws IOException {
		 servidor = new ServerSocket(porta);
		 new Thread(this).start();
		 System.out.println("Servidor na porta:"+porta);
		
		
	}
	
	
	
}
