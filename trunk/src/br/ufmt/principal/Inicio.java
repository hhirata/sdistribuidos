package br.ufmt.principal;

public class Inicio {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Thread t= new Thread(new ClienteC("192.168.1.3",1024));
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
