package br.ufmt.principal;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

public class RandomAccessFileEx {

	static final String FILEPATH = "D:\\Data.pdf";
	static File  arquivo= new File(FILEPATH);
	static final String FILEPATH2 = "D:\\outro.mp3";
	static int tamanho = (int)arquivo.length();
	static int cont =0;
	public static void main(String[] args) {

/*		try {
		
			while(cont < tamanho){
				byte[]b = readFromFile(FILEPATH, cont,1024);
				writeToFile(FILEPATH2, b, cont);
				System.out.println(cont);
				cont=cont+b.length;
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
		System.out.println(tamanho);

	}

	 static byte[] readFromFile(String filePath, int position, int size)
			throws IOException {

		RandomAccessFile file = new RandomAccessFile(filePath, "r");
		file.seek(position);
		byte[] bytes = new byte[size];
		file.read(bytes);
		file.close();
		return bytes;

	}

 public void writeToFile(String filePath, byte[] data, int position)
			throws IOException {

		RandomAccessFile file = new RandomAccessFile(filePath, "rwd");
		file.seek(position);
		file.write(data);
		file.close();
		

	}
 
 public void criar(String caminho){
	 try {
		FileOutputStream fl = new FileOutputStream(caminho);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
 }
 
 public void escrever(String caminho,byte[] data, int pos,int tamanho){
	 try {
		FileOutputStream fl = new FileOutputStream(caminho);
		fl.write(data, pos, tamanho);
		fl.flush();
		fl.close();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
 }
}