package fr.fms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.fms.entities.Order;
import fr.fms.entities.ShoppingCart;

public class OrderDao<T> implements Dao<T> {

	@Override
	public void create(T obj) {
		try {
			String strSql = "INSERT INTO T_Order (IdUser, Date) VALUES (?,?);";
			try(PreparedStatement ps = connexion.executeUpdate(strSql)){
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
		Order order = null;
		try {
			String strSql = "SELECT * FROM T_Order where idUser = " + id;
			ResultSet resultSet = connexion.executeQuery(strSql);
			while(resultSet.next()) {
				order = new Order(resultSet.getInt(1),resultSet.getInt(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return (T) order;
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

}
