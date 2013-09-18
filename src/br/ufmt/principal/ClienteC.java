package br.ufmt.principal;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.xml.bind.JAXBException;

import javafx.scene.control.TextArea;
import br.ufmt.busca.Busca;
import br.ufmt.checksum.CheckSum;
import br.ufmt.dados.DadosArquivo;
import br.ufmt.dados.TrataXMLDados;
import br.ufmt.publica.Publica;
import br.ufmt.publica.TrataXmlPlub;
import br.ufmt.requisicao.RequisitaArquivo;
import br.ufmt.requisicao.TrataXMLReq;

public class ClienteC implements Runnable {

	private String servidorString;
	private int porta;
	private int modo=0;
	private String caminho;
	private File req;


	public ClienteC(int porta, int modo, String caminho, File req) {
		super();
		this.porta = porta;
		this.modo = modo;
		this.caminho = caminho;
		this.req = req;
	}
	public String getServidorString() {
		return servidorString;
	}
	public void setServidorString(String servidorString) {
		this.servidorString = servidorString;
	}
	public int getPorta() {
		return porta;
	}
	public void setPorta(int porta) {
		this.porta = porta;
	}
	public ClienteC(String servidorString, int porta) {
		this.servidorString = servidorString;
		this.porta = porta;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{

			//Solicitando e enviando um arquivo pequeno 
			/* Socket s = new Socket(servidorString, porta);
        ObjectOutputStream oo = new ObjectOutputStream(s.getOutputStream());

        RequisitaArquivo rq = new RequisitaArquivo();
        rq.setNome("programa2");
        rq.setPosicao(0);
        rq.setTamanho(441);

        String string = new TrataXML().criarXmlReq(rq);
        oo.writeObject(string);

     //   ObjectInputStream oi = new ObjectInputStream(s.getInputStream());

       DataInputStream dt = new DataInputStream(s.getInputStream());

       int i = dt.readInt();
       byte []b = new byte[i];
       dt.readFully(b, 0,b.length);
       System.out.println(b);

       RandomAccessFileEx.writeToFile("D:\\Downloads\\programa2",b ,0);



        s.close();*/



			//Montando o arquivo em partes na memória via localhost

			/*			int tamanho=84398520;
			int cont =0;
			int qtd=8192;
			byte[] fil = new byte[tamanho];

			while(tamanho > 0){
				if(qtd>tamanho){
					qtd=tamanho;

				}
				Socket s = new Socket(servidorString, porta);
				ObjectOutputStream oo = new ObjectOutputStream(s.getOutputStream());
				RequisitaArquivo rq = new RequisitaArquivo();
				rq.setNome("NS330.rar");
				rq.setPosicao(cont);
				rq.setTamanho(qtd);

				String string = new TrataXML().criarXmlReq(rq);
				oo.writeObject(string);

				DataInputStream dt = new DataInputStream(s.getInputStream());

				int i = dt.readInt();
				//byte []b = new byte[i];
				dt.readFully(fil,cont,i);

				cont= cont+qtd;
				tamanho= tamanho- qtd;

				s.close();

			}
			 RandomAccessFileEx hand = new RandomAccessFileEx();
			 hand.writeToFile("D:\\Downloads\\NS330.rar",fil ,0);*/


			//multiplas partes em disco

			/*			int tamanho=177664;
			int cont =0;
			int qtd=1024;
			RandomAccessFileEx hand2 = new RandomAccessFileEx();
			byte[]bts = new byte[tamanho];
			RandomAccessFile arquivo = new RandomAccessFile("D:\\Downloads\\estrutura.doc", "rw");
			while(tamanho > 0){
				if(qtd>tamanho){
					qtd=tamanho;

				}
				Socket s = new Socket(servidorString, porta);
				ObjectOutputStream oo = new ObjectOutputStream(s.getOutputStream());
				RequisitaArquivo rq = new RequisitaArquivo();
				rq.setNome("estrutura.doc");
				rq.setPosicao(cont);
				rq.setTamanho(qtd);

				String string = new TrataXML().criarXmlReq(rq);
				oo.writeObject(string);

				DataInputStream dt = new DataInputStream(s.getInputStream());

				int i = dt.readInt();
				byte []b = new byte[i];
				dt.readFully(b,0,b.length);

				RandomAccessFileEx hand = new RandomAccessFileEx();
				hand.writeToFile("D:\\Downloads\\11.rar", b, cont);

				new Thread(new ManipulaArquivo("D:\\Downloads\\estrutura.doc", b, cont,arquivo)).start();;
				cont= cont+qtd;
				tamanho= tamanho- qtd;

				s.close();
			}
			arquivo.close();*/

			//Solicitando de outros clientes e montando
			/*			ArrayList<String> ips = new ArrayList<>();
			ips.add("192.168.1.3");
			ips.add("192.168.1.4");
			int max = ips.size();
			int contador=0;
			int tamanho=29918871;
			int cont =0;
			int qtd=32768;
			System.out.println(max);
			RandomAccessFileEx hand2 = new RandomAccessFileEx();
			byte[]bts = new byte[tamanho];
			RandomAccessFile arquivo = new RandomAccessFile("D:\\Downloads\\Data.pdf", "rw");
			while(tamanho > 0){
				if(qtd>tamanho){
					qtd=tamanho;

				}

				String end= ips.get(contador);
				Socket s = new Socket(end, porta);

				ObjectOutputStream oo = new ObjectOutputStream(s.getOutputStream());
				RequisitaArquivo rq = new RequisitaArquivo();
				rq.setNome("Data.pdf");
				rq.setPosicao(cont);
				rq.setTamanho(qtd);

				String string = new TrataXML().criarXmlReq(rq);
				oo.writeObject(string);

				DataInputStream dt = new DataInputStream(s.getInputStream());

				int i = dt.readInt();
				byte []b = new byte[i];
				dt.readFully(b,0,b.length);
				System.out.println("Recebendo de "+end);
				RandomAccessFileEx hand = new RandomAccessFileEx();
				hand.writeToFile("D:\\Downloads\\11.rar", b, cont);
				if(contador+1 <max){
					contador++;
				}
				else{
					contador=0;

				}
				new Thread(new ManipulaArquivo("D:\\Downloads\\Data.pdf", b, cont,arquivo)).start();;
				cont= cont+qtd;
				tamanho= tamanho- qtd;

				s.close();
			}
			arquivo.close();*/
			if(modo==1){

				DadosArquivo dados = new TrataXMLDados().le(req);

				ArrayList<String> ips = new ArrayList<>();

				ips=(ArrayList<String>) dados.getIp();

				int contador=0;
				int tamanho=dados.getTamanho();
				int tamanho2=dados.getTamanho();
				String check=dados.getMd5();
				int cont =0;
				int qtd=32768;
				byte[]bts = new byte[tamanho];
				RandomAccessFile arquivo = new RandomAccessFile(caminho+"\\"+dados.getNome(), "rw");
				String nome = dados.getNome();

				while(tamanho > 0){
					if(qtd>tamanho){
						qtd=tamanho;

					}
					String end= ips.get(contador);
					RequisitaArquivo rq = new RequisitaArquivo();
					rq.setNome(nome);
					rq.setPosicao(cont);
					rq.setTamanho(qtd);

					String req = new TrataXMLReq().criarXmlReq(rq);
					Thread	t = new Thread(new ClienteCServerC(1024, end, req, arquivo,cont,ips));
					t.start();
					t.join();
					if(contador+1 <ips.size()){
						contador++;
					}
					else{
						contador=0;

					}
					cont= cont+qtd;
					tamanho= tamanho- qtd;

				}

				arquivo.close();
				CheckSum ck = new CheckSum(caminho+"\\"+nome);
				String md5=ck.calculaMD5();
				if(md5.equals(check)){
					//publica
					System.out.println("Arquivo Completo");
					InetAddress addr;
					try {
						addr = InetAddress.getLocalHost();
						Publica pb = new Publica();
						pb.setNome(nome);
						pb.setTamanho(tamanho2);
						pb.setMd5(check);
						pb.setIp(addr.getHostAddress());
						String bff = new TrataXmlPlub().criaXmlPubl(pb); 
						System.out.println(bff);

					} catch (UnknownHostException | JAXBException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}else{

					System.out.println("Erro");
					System.out.println(md5);
				}
			}else if(modo==2){

			}
		}catch (Exception e){
			e.printStackTrace();
		}

	}

}
