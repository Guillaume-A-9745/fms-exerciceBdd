package fr.fms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import fr.fms.entities.User;

public class UserDao<T> implements Dao<T>{
	ConfigReader conf = new ConfigReader();
	Properties prop = conf.ConfigReader("config.properties");
	CreateConfFile connexion = new CreateConfFile(prop.getProperty("driver"),prop.getProperty("url"),prop.getProperty("login"),prop.getProperty("password"));
	
	@Override
	public void create(T obj) {
		try {
			connexion.getConnection();
			String StrSql = "INSERT INTO T_Users (Login, Password) VALUES (?,?);";
			try(PreparedStatement ps = connexion.executeUpdate(StrSql)){
				ps.setString(1, ((User) obj).getLogin());
				ps.setString(2, ((User) obj).getPassword());
				if(ps.executeUpdate() == 1)		
					System.out.println("Insertion ok");}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	@Override
	public T read(int id) {
		User user = null;
		try {
			connexion.getConnection();
			String StrSql = "SELECT * FROM T_Users where idUser = " + id;
			ResultSet resultSet = connexion.executeQuery(StrSql);
			while(resultSet.next()) {
				user = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
			}
			System.out.println(user);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}

	@Override
	public boolean update(T obj) {	
		try {
			connexion.getConnection();
			String StrSql = "update T_Users set Login=?, Password=? where IdUser=?";
			try(PreparedStatement ps = connexion.executeUpdate(StrSql)){		
				ps.setString(1, ((User) obj).getLogin());
				ps.setString(2, ((User) obj).getPassword());
				ps.setInt(3, ((User) obj).getId());
				if(ps.executeUpdate() == 1)
					System.out.println("Update ok");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return false;
	}

	@Override
	public boolean delete(T obj) {
		try {
			connexion.getConnection();
			String StrSql = "delete from T_Users where IdUser=?";
			try(PreparedStatement ps = connexion.executeUpdate(StrSql)){
				ps.setDouble(1, ((User) obj).getId());
				if(ps.executeUpdate() == 1)
					System.out.println("Suppression ok");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return false;
	}

	@Override
	public ArrayList<T> readAll() {
		ArrayList<User> users = new ArrayList<User>();	
		try {
			connexion.getConnection();
			String StrSql = "SELECT * FROM T_Users";
			ResultSet resultSet = connexion.executeQuery(StrSql);
			while(resultSet.next()) {
				int rsIdUser = resultSet.getInt(1);
				String rsLogin = resultSet.getString(2);
				String rsPassword = resultSet.getString(3);
				users.add((new User(rsIdUser,rsLogin,rsPassword)));
			}
			for(User a : users)
				System.out.println(a);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}

}