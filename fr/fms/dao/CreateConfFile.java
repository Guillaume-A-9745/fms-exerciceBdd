package fr.fms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class CreateConfFile {
	ConfigReader conf = new ConfigReader();
	Properties prop = conf.ConfigReader("config.properties");
	
	private final String driver = prop.getProperty("driver");
	private final String url = prop.getProperty("url");
	private final String login = prop.getProperty("login");
	private final String password = prop.getProperty("password");
	private Connection conn;
	
	private static final CreateConfFile INSTANCE = new CreateConfFile();

	//Connection à la base de donnée
	private CreateConfFile() {
		if (conn == null) {
			try {
				Class.forName(driver);
				conn = DriverManager.getConnection(url,login,password);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
	}

	public static CreateConfFile getInstance() {
		return INSTANCE;
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
