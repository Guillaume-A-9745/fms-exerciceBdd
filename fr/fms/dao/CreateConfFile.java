package fr.fms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateConfFile {
	private final String driver;
	private final String url;
	private final String login;
	private final String password;
	private Connection conn;


	public CreateConfFile(String driver, String url,String login,String password) {
		this.driver = driver;
		this.url = url;
		this.login = login;
		this.password = password;
	}

	//Connection à la base de donnée
	public Connection getConnection() throws SQLException {
		if(conn == null) {
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}		
			conn = DriverManager.getConnection(url,login,password);	
		}
		return conn;

	}

	//Execute les requêtes SELECT  retourne un objet ResultSet
	public ResultSet executeQuery(String Query) throws SQLException {
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(Query);
		return rs;
	}

	//Execute les méthodes INSERT UPDATE DELETE
	public PreparedStatement executeUpdate(String Query) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(Query);
		return ps;
	}

	//Ferme la connection à la bdd
	public void closeConnection() throws SQLException{
		if(conn != null) {
			conn.close();
		}
	}
}
