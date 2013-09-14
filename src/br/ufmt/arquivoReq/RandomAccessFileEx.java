package br.ufmt.arquivoReq;
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

public byte[] readFromFile(String filePath, int position, int size)
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
 

}