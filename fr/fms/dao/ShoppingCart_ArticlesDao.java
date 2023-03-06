package fr.fms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.fms.entities.Article;
import fr.fms.entities.ShoppingCart;
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
				ps.executeUpdate();	
			}
		} catch (SQLException e) {
			System.out.println("Article inexitant " + e.getMessage());	
		} 
	}

	@Override
	public T read(int id) {
		ShoppingCart_Articles article = null;
		try {
			//String sql = "SELECT T_Articles.IdArticle,Description,Brand,UnitaryPrice,Quantity FROM T_ShoppingCart_Articles inner join T_Articles on T_ShoppingCart_Articles.IdArticle = T_Articles.IdArticle where T_ShoppingCart_Articles.IdCart like "+ id;
			String sql = "SELECT T_Articles.IdArticle,Description,Brand,UnitaryPrice,Quantity FROM T_Articles inner join T_ShoppingCart_Articles on T_ShoppingCart_Articles.IdArticle = T_Articles.IdArticle where T_ShoppingCart_Articles.IdCart = "+ id;
			ResultSet resultSet2 = connexion.executeQuery(sql);
			while(resultSet2.next()) {
				int idArticle = resultSet2.getInt("IdArticle");
				String description = resultSet2.getString("Description");
				String brand= resultSet2.getString("Brand");
				int unataryPrice = resultSet2.getInt("UnitaryPrice");
				int quantity = resultSet2.getInt("Quantity");
				article = new ShoppingCart_Articles(idArticle,description,brand,unataryPrice,quantity);
				System.out.println(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}

	@Override
	public boolean update(T obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(T obj) {
		try {
			String StrSql = "delete from T_ShoppingCart_Articles where IdArticle=?";
			try(PreparedStatement ps = connexion.executeUpdate(StrSql)){
				ps.setDouble(1, ((Article) obj).getId());
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
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteArticle(int id) {
		try {
			String strSql = "SELECT * FROM T_ShoppingCart_Articles where IdArticle = " + id;
			ResultSet resultSet = connexion.executeQuery(strSql);
			if(resultSet.next()) {
				String sql = "delete from T_ShoppingCart_Articles where IdArticle =?";
				try(PreparedStatement ps = connexion.executeUpdate(sql)){
					ps.setDouble(1, id);
					if(ps.executeUpdate() == 1)
						System.out.println("Suppression ok");
				}
			}else {
				System.out.println("Erreur de saissi, cette article  n'est pas dans votre liste.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteAllArticle(int id) {
		String sql = "delete from T_ShoppingCart_Articles where IdCart =?";
		try(PreparedStatement ps = connexion.executeUpdate(sql)){
			ps.setDouble(1, id);
			if(ps.executeUpdate() == 1)
				System.out.println("Suppression ok");
		}catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
