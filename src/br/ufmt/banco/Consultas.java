package br.ufmt.banco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufmt.dados.DadosArquivo;
import br.ufmt.publica.Publica;

public class Consultas {

	public Consultas() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean buscaIp(String ip) throws SQLException{
		Connection c = new BancoDados().getConnection();
	     List<Ip> lista = new ArrayList<>();
	     PreparedStatement stm = c.prepareStatement("SELECT * FROM clientes where  ip like ?");
	     stm.setString(1, ip);
	     
	     ResultSet rs = stm.executeQuery();
	     while(rs.next()){
	    	 Ip i = new Ip();
	    	 i.setIp(rs.getString("ip"));
	    	 lista.add(i);
	     }
	     rs.close();
	     stm.close();
	     c.close();
	     if(lista.size()>0){
	    	 return true;
	    	 
	     }else{
	    	 return false;
	     }
	     
		
	}
	
	public boolean Existe(String nome, String ip) throws SQLException{
		Connection c = new BancoDados().getConnection();
	     List<Ip> lista = new ArrayList<>();
	     PreparedStatement stm = c.prepareStatement("SELECT * FROM arquivo where nome like ? and ip like ?");
	     stm.setString(1, nome);
	     stm.setString(2, ip);
	     ResultSet rs = stm.executeQuery();
	     while(rs.next()){
	    	 Ip i = new Ip();
	    	 i.setIp(rs.getString("ip"));
	    	 lista.add(i);
	     }
	     rs.close();
	     stm.close();
	     c.close();
	     if(lista.size()>0){
	    	 return true;
	    	 
	     }else{
	    	 return false;
	     }
	     
		
	}
	public List<String> buscaIps(String nome) throws SQLException{
		Connection c = new BancoDados().getConnection();
	     List<String> lista = new ArrayList<>();
	     PreparedStatement stm = c.prepareStatement("SELECT ip FROM arquivo where nome like ? group by ip");
	     stm.setString(1, nome);
	     
	     ResultSet rs = stm.executeQuery();
	     while(rs.next()){
	    	 String i= new String(rs.getString("ip"));
	      	 lista.add(i);
	     }
	     rs.close();
	     stm.close();
	     c.close();
	     return  lista;
	     
		
	}
	
	public void InsereIp(String ip) throws SQLException{
		Connection c = new BancoDados().getConnection();
	    PreparedStatement stm = c.prepareStatement("Insert into clientes values(?)");
	    stm.setString(1, ip);
	    int  i = stm.executeUpdate();
	    c.close();
	     
		
	}
	
	public void insereArquivo(Publica pb){
		Connection c = new BancoDados().getConnection();
	    PreparedStatement stm;
		try {
			Consultas con= new Consultas();
			
			if(!con.buscaIp(pb.getIp())){
			 con.InsereIp(pb.getIp());
			 stm = c.prepareStatement("Insert into arquivo(nome,md5,tamanho,ip) values(?,?,?,?)");
			 stm.setString(1,pb.getNome());
			 stm.setString(2, pb.getMd5());
			 stm.setInt(3, pb.getTamanho());
			 stm.setString(4, pb.getIp());
			 stm.executeUpdate();
			}else{
				 stm = c.prepareStatement("Insert into arquivo(nome,md5,tamanho,ip) values(?,?,?,?)");
				 stm.setString(1,pb.getNome());
				 stm.setString(2, pb.getMd5());
				 stm.setInt(3, pb.getTamanho());
				 stm.setString(4, pb.getIp());
				 stm.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
	    
		
	}
	
		
	public DadosArquivo buscaArquivo(String nome) throws SQLException{
		Connection c = new BancoDados().getConnection();
		DadosArquivo  dados = new DadosArquivo();
		PreparedStatement stm = c.prepareStatement("SELECT nome FROM arquivo where  nome like ? group by nome");
		stm.setString(1, nome);
		ResultSet rs = stm.executeQuery();
		if(rs.next()){
		dados.setNome(rs.getString("nome"));
		stm= c.prepareStatement("SELECT md5 FROM arquivo where  nome like ? group by md5");
		stm.setString(1, nome);
		rs=stm.executeQuery();
		if(rs.next()){
		dados.setMd5(rs.getString("md5"));
		stm= c.prepareStatement("SELECT tamanho FROM arquivo where  nome like ? group by tamanho");
		stm.setString(1, nome);
		rs=stm.executeQuery();
		if (rs.next()){
		dados.setTamanho(rs.getInt("tamanho"));
		List<String>ips = new Consultas().buscaIps(nome);
		dados.setIp(ips);
		}
		}
		}
		c.close();
		return dados;

		
		
	}
	
	public static void main(String[] args) {
		
		 Consultas con = new Consultas();
		 try {
			DadosArquivo d = con.buscaArquivo("Data.pdf");
			System.out.println(d.getTamanho());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
