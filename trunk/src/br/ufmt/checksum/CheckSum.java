package br.ufmt.checksum;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class CheckSum {

	private String arquivo;
	public CheckSum() {

	}


	public CheckSum(String arquivo) {
		super();
		this.arquivo = arquivo;
	}


	public String getArquivo() {
		return arquivo;
	}


	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}

	public String calculaMD5(){
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			FileInputStream file = new FileInputStream(arquivo);
			byte[] dados = new byte[1024];
			int cont =0;
			while((cont=file.read(dados))!=-1){
				md.update(dados, 0, cont);
			}
			byte [] md5 = md.digest();

			StringBuffer buffer = new StringBuffer();
			for (int i = 0; i < md5.length; i++) {
				buffer.append(Integer.toString((md5[i] & 0xff) + 0x100, 16).substring(1));
			}
			return buffer.toString();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CheckSum ck = new CheckSum("D:\\Data.pdf");
		System.out.println(ck.calculaMD5());

	}

}
