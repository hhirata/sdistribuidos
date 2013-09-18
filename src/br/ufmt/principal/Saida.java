package br.ufmt.principal;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import javafx.scene.control.TextArea;

public class Saida implements Runnable {
	
	private TextArea txt;
	private ByteArrayOutputStream baos;
	
	public Saida(TextArea txt) {
		super();
		this.txt = txt;
	}

	public Saida(TextArea txt, ByteArrayOutputStream b) {
		super();
		this.txt = txt;
		this.baos=b;
	}
	public TextArea getTxt() {
		return txt;
	}

	public void setTxt(TextArea txt) {
		this.txt = txt;
	}

	public Saida() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		txt.setText(baos.toString());
	}

}
