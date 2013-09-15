package br.ufmt.principal;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.net.Socket;
import java.net.UnknownHostException;

import br.ufmt.arquivoReq.ManipulaArquivo;

public class ClienteCServerC implements Runnable {

	private Socket s;
	private int porta;
	private String endereco;
	private String requisicao;
	private RandomAccessFile arquivo;
	private int posicao;
	
	
	
	public ClienteCServerC(int porta, String endereco, String requisicao,
			RandomAccessFile arquivo, int posicao) {
		super();
		this.porta = porta;
		this.endereco = endereco;
		this.requisicao = requisicao;
		this.arquivo = arquivo;
		this.posicao = posicao;
	}

	public ClienteCServerC(Socket s, int porta, String endereco,
			String requisicao, RandomAccessFile arquivo, int posicao) {
		this.s = s;
		this.porta = porta;
		this.endereco = endereco;
		this.requisicao = requisicao;
		this.arquivo = arquivo;
		this.posicao = posicao;
	}

	public String getRequisicao() {

		return requisicao;
	}

	public void setRequisicao(String requisicao) {
		this.requisicao = requisicao;
	}

	public Socket getS() {
		return s;
	}

	public void setS(Socket s) {
		this.s = s;
	}

	public int getPorta() {
		return porta;
	}

	public void setPorta(int porta) {
		this.porta = porta;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public RandomAccessFile getArquivo() {
		return arquivo;
	}

	public void setArquivo(RandomAccessFile arquivo) {
		this.arquivo = arquivo;
	}

	public int getPosicao() {
		return posicao;
	}

	public void setPosicao(int posicao) {
		this.posicao = posicao;
	}

	public ClienteCServerC() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {

		try {
			s = new Socket(endereco, porta);
			ObjectOutputStream oo = new ObjectOutputStream(s.getOutputStream());
			oo.writeObject(requisicao);
			DataInputStream dt = new DataInputStream(s.getInputStream());
			
			int i = dt.readInt();
			if(i==-1){
				System.out.println("erro");
				System.exit(1);
			}
			byte []b = new byte[i];
			dt.readFully(b,0,b.length);
			System.out.println("Recebendo de "+ endereco);
			Thread t= new Thread(new ManipulaArquivo( b, posicao, arquivo));
			t.start();
			t.join();
			s.close();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}

}
