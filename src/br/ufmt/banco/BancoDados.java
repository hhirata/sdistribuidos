package br.ufmt.banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BancoDados {

	public BancoDados() {
		// TODO Auto-generated constructor stub
	}
	
	public Connection getConnection(){
	       try {
	            return DriverManager.getConnection(
	          "jdbc:postgresql://localhost/SD", "postgres", "postgres");
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	}
}
