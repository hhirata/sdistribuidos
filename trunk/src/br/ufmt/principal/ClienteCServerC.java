package br.ufmt.principal;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javafx.scene.control.TextArea;
import br.ufmt.arquivoReq.ManipulaArquivo;

public class ClienteCServerC implements Runnable {

	private Socket s;
	private int porta;
	private String endereco;
	private String requisicao;
	private RandomAccessFile arquivo;
	private int posicao;
	private ArrayList<String>ips;
	
	
	
	public ClienteCServerC(Socket s, int porta, String endereco,
			String requisicao, RandomAccessFile arquivo, int posicao,
			ArrayList<String> ips) {
		super();
		this.s = s;
		this.porta = porta;
		this.endereco = endereco;
		this.requisicao = requisicao;
		this.arquivo = arquivo;
		this.posicao = posicao;
		this.ips = ips;
	}

	public ArrayList<String> getIps() {
		return ips;
	}

	public void setIps(ArrayList<String> ips) {
		this.ips = ips;
	}

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

	public void removeIP(){
		if(ips.contains(endereco)){
			ips.remove(endereco);			
		}
	}
	
	public void novoEndereco(){
		int pos = ips.indexOf(endereco);
		this.removeIP();
		int tam = ips.size();
		if(tam >= pos){
			endereco=ips.get(0);
		}else if(tam==0){
			
		}
	}
	public ClienteCServerC() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {

		try {
			s = new Socket(endereco, porta);
			s.setSoTimeout(60000);
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
//			txt.appendText("\nRecebendo de "+ endereco);
			Thread t= new Thread(new ManipulaArquivo( b, posicao, arquivo));
			t.start();
			t.join();
			s.close();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			   System.out.println("Erro ao receber de "+endereco);
				this.novoEndereco();
				Thread t = new Thread(new ClienteCServerC(porta, endereco, requisicao, arquivo, posicao, ips));
				t.start();
				try {
					t.join();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		//	e.printStackTrace();
		}catch(ConnectException e){
			
			System.out.println("Erro ao receber de "+endereco);
			this.novoEndereco();
			Thread t = new Thread(new ClienteCServerC(porta, endereco, requisicao, arquivo, posicao, ips));
			t.start();
			try {
				t.join();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		catch (IOException e) {
			System.out.println("Erro ao receber de "+endereco);
			this.novoEndereco();
			Thread t = new Thread(new ClienteCServerC(porta, endereco, requisicao, arquivo, posicao, ips));
			t.start();
			try {
				t.join();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		//e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("Erro ao receber de "+endereco);
			this.novoEndereco();
			Thread t = new Thread(new ClienteCServerC(porta, endereco, requisicao, arquivo, posicao, ips));
			t.start();
			try {
				t.join();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		//e.printStackTrace();
		
		}catch(Exception e){
			
			e.printStackTrace();
			
		}



	}

	public ClienteCServerC(int porta, String endereco, String requisicao,
			RandomAccessFile arquivo, int posicao, ArrayList<String> ips) {
		super();
		this.porta = porta;
		this.endereco = endereco;
		this.requisicao = requisicao;
		this.arquivo = arquivo;
		this.posicao = posicao;
		this.ips = ips;
	}

}
