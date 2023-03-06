package fr.fms.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.fms.entities.ShoppingCart_Articles;

public class ShoppingCart_ArticlesDao<T> implements Dao<T> {

	@Override
	public void create(T obj) {
		try {
			String StrSql = "INSERT INTO T_ShoppingCart_Articles (IdCart, IdArticle, Quantity) VALUES (?,?,?);";
			try(PreparedStatement ps = connexion.executeUpdate(StrSql)){
				ps.setInt(1, ((ShoppingCart_Articles) obj).getIdCart());
				ps.setInt(2, ((ShoppingCart_Articles) obj).getIdArticles());
				ps.setInt(3, ((ShoppingCart_Articles) obj).getQuantity());
				if(ps.executeUpdate() == 1)		
					System.out.println("Insertion ok");}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	@Override
	public T read(int id) {
		// TODO Auto-generated method stub
		return null;
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
