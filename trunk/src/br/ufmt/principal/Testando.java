package br.ufmt.principal;

import java.io.IOException;

public class Testando {

	public static void main(String[] args) {
				
			try {
				new ServidorC(1024);
				Thread cliente= new Thread(new ClienteC("192.168.1.3", 1024));
				cliente.start();
				cliente.join();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

}
