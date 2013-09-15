package br.ufmt.principal;

import java.io.IOException;
import java.net.ServerSocket;

public class ServidorC implements Runnable {

	
	ServerSocket servidor;
	@Override
	public void run() {
		
		while(true){
			try {
				String caminho = new String("D:\\");
				new Thread(new TrataReqCliente(servidor.accept(),caminho )).start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public ServidorC(int porta) throws IOException {
		servidor = new ServerSocket(porta);
		new Thread(this).start();
		System.out.println("Servidor na porta:"+porta);
		
		
	}
	 public static void main(String[] args) {
		 try {
			new ServidorC(1024);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
