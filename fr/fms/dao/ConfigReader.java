package fr.fms.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

public class ConfigReader {

	//	public static void main(String[] args) throws IOException, SQLException {
	//		Properties prop = ConfigReader("config.properties");
	//		CreateConfFile connexion = new CreateConfFile(prop.getProperty("driver"),prop.getProperty("url"),prop.getProperty("login"),prop.getProperty("password"));
	//		connexion.getConnection();
	//	}
	
	public Properties ConfigReader(String filename) {
		FileInputStream fis = null;
		Properties prop = null;
		try {
			fis = new FileInputStream(filename);
			prop = new Properties();
			prop.load(fis);
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return prop;
	}
}
