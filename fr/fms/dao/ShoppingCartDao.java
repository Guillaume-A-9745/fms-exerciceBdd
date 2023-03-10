package fr.fms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.fms.entities.ShoppingCart;
import fr.fms.entities.User;

public class ShoppingCartDao<T> implements Dao<T> {

	@Override
	public void create(T obj) {
		try {
			String StrSql = "INSERT INTO T_ShoppingCart (IdUser, Date) VALUES (?,?);";
			try(PreparedStatement ps = connexion.executeUpdate(StrSql)){
				ps.setInt(1, ((ShoppingCart) obj).getIdUser());
				ps.setString(2, ShoppingCart.getDate().toString());
				if(ps.executeUpdate() == 1)		
					System.out.println("Insertion ok");}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	@Override
	public T read(int id) {
		ShoppingCart cart = null;
		try {
			String StrSql = "SELECT * FROM T_ShoppingCart where idUser = " + id;
			ResultSet resultSet = connexion.executeQuery(StrSql);
			while(resultSet.next()) {
				cart = new ShoppingCart(resultSet.getInt(1),resultSet.getInt(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return (T) cart;
	}

	@Override
	public boolean update(T obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(T obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<T> readAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public T readCart(int id) {
		ShoppingCart cart = null;
		try {
			String StrSql = "SELECT Login FROM T_ShoppingCart inner join T_Users where T_ShoppingCart.idUser = T_Users.IdUser and T_ShoppingCart.idUser =" + id;
			ResultSet resultSet = connexion.executeQuery(StrSql);
			while(resultSet.next()) {
				cart = new ShoppingCart(resultSet.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return (T) cart;
	}

}
