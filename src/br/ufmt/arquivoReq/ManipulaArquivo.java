package br.ufmt.arquivoReq;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class ManipulaArquivo implements Runnable {

	private String caminho;
	private byte [] data;
	private int position;
	private RandomAccessFile file;


	public ManipulaArquivo(String caminho, byte[] data, int position) {
		super();
		this.caminho = caminho;
		this.data = data;
		this.position = position;
		
	}
	

	public ManipulaArquivo(String caminho, byte[] data, int position,
			RandomAccessFile file) {
		super();
		this.caminho = caminho;
		this.data = data;
		this.position = position;
		this.file = file;
	}


	public RandomAccessFile getFile() {
		return file;
	}


	public void setFile(RandomAccessFile file) {
		this.file = file;
	}


	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	
	
	public ManipulaArquivo(byte[] data, int position, RandomAccessFile file) {
		super();
		this.data = data;
		this.position = position;
		this.file = file;
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub

		try {
			file.getChannel();
			file.seek(position);
			file.write(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
}

}
