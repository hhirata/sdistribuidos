package br.ufmt.principal;

import java.io.IOException;
import java.io.OutputStream;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

public class Console extends OutputStream {
	
	private TextArea txt;

	public Console() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void write(final int i) throws IOException {
		// TODO Auto-generated method stub
		Platform.runLater(new Runnable() {
	        public void run() {
	            txt.appendText(String.valueOf((char) i));
	        }});
	}

	public Console(TextArea txt) {
		super();
		this.txt = txt;
	}

	public TextArea getTxt() {
		return txt;
	}

	public void setTxt(TextArea txt) {
		this.txt = txt;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
