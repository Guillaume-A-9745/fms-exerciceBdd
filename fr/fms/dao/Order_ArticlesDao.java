package fr.fms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.fms.entities.Order;
import fr.fms.entities.Order_Articles;
import fr.fms.entities.ShoppingCart;
import fr.fms.entities.ShoppingCart_Articles;

public class Order_ArticlesDao<T> implements Dao<T> {

	@Override
	public void create(T obj) {
		try {
			String strSql = "INSERT INTO T_Order_Articles (IdCart, IdArticle, Quantity) VALUES (?,?,?);";
			try(PreparedStatement ps = connexion.executeUpdate(strSql)){
				ps.setInt(1, ((Order_Articles) obj).getIdCart());
				ps.setInt(2, ((Order_Articles) obj).getIdArticles());
				ps.setInt(3, ((Order_Articles) obj).getQuantity());
				ps.executeUpdate();	
			}
		} catch (SQLException e) {
			System.out.println("Article inexitant " + e.getMessage());	
		}

	}

	@Override
	public T read(int id) {
		Order_Articles article = null;
		try {
			//String sql = "SELECT T_Articles.IdArticle,Description,Brand,UnitaryPrice,Quantity FROM T_ShoppingCart_Articles inner join T_Articles on T_ShoppingCart_Articles.IdArticle = T_Articles.IdArticle where T_ShoppingCart_Articles.IdCart like "+ id;
			String sql = "SELECT T_Articles.IdArticle,Description,Brand,UnitaryPrice,Quantity FROM T_Articles inner join T_Order_Articles on T_Order_Articles.IdArticle = T_Articles.IdArticle where T_Order_Articles.IdCart = "+ id;
			ResultSet resultSet2 = connexion.executeQuery(sql);
			while(resultSet2.next()) {
				int idArticle = resultSet2.getInt("IdArticle");
				String description = resultSet2.getString("Description");
				String brand= resultSet2.getString("Brand");
				int unataryPrice = resultSet2.getInt("UnitaryPrice");
				int quantity = resultSet2.getInt("Quantity");
				article = new Order_Articles(idArticle,description,brand,unataryPrice,quantity);
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<T> readAll() {
		// TODO Auto-generated method stub
		return null;
	}


	//recuperer le panier et le mettre dans  la table order
	public void createOrder(T obj) {
		try {
			String strSql = "INSERT INTO T_Order (IdUser, Date) VALUES (?,?);";	
			try(PreparedStatement ps = connexion.executeUpdate(strSql)){
				ps.setInt(1, ((Order) obj).getIdUser());
				ps.setString(2, Order.getDate().toString());
				ps.executeUpdate();	
				ResultSet generatedKeys  = ps.getGeneratedKeys();
				if(generatedKeys.next()) {
					int orderId = generatedKeys.getInt(1);
					String sql = "INSERT INTO T_Order_Articles (IdOrder, IdArticle, Quantity)"+ "SELECT ?,IdArticle,Quantity FROM T_ShoppingCard_Article WHERE IdCart = ?";
					try(PreparedStatement ps2 = connexion.executeUpdate(sql)){
						ps2.setInt(1, orderId);
						ps2.setInt(2, ((Order_Articles) obj).getIdCart());
						ps2.executeUpdate();	
					}
				}
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}
}