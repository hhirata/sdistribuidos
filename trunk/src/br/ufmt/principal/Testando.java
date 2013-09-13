package br.ufmt.principal;

import java.io.IOException;

public class Testando {

	public static void main(String[] args) {
	   new Thread(new ClienteC("192.168.1.3", 1024)).start();
		
	}

}
