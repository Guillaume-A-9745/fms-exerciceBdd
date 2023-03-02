package fr.fms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.fms.entities.Article;

public class ArticleDao<T> implements Dao<T> {
	//CreateConfFile connexion = CreateConfFile.getInstance();
	
	@Override
	public void create(T obj) {
		try {
			String StrSql = "INSERT INTO T_Articles (Description,Brand, UnitaryPrice) VALUES (?,?,?);";
			try(PreparedStatement ps = connexion.executeUpdate(StrSql)){
				ps.setString(1, ((Article) obj).getDescription());
				ps.setString(2, ((Article) obj).getBrand());
				ps.setDouble(3, ((Article) obj).getPrice());
				if(ps.executeUpdate() == 1)		
					System.out.println("Insertion ok");}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	@Override
	public T read(int id) {
		Article article = null;
		try {
			String StrSql = "SELECT * FROM T_Articles where idArticle = " + id;
			ResultSet resultSet = connexion.executeQuery(StrSql);
			while(resultSet.next()) {
				article = new Article(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getDouble(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return (T) article;
	}

	@Override
	public boolean update(T obj) {	
		try {
			String StrSql = "update T_Articles set Description=?, Brand=?, UnitaryPrice=? where IdArticle=?";
			try(PreparedStatement ps = connexion.executeUpdate(StrSql)){
				ps.setString(1, ((Article) obj).getDescription());
				ps.setString(2, ((Article) obj).getBrand());
				ps.setDouble(3, ((Article) obj).getPrice());
				ps.setDouble(4, ((Article) obj).getId());
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
			String StrSql = "delete from T_Articles where IdArticle=?";
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
		ArrayList<Article> articles = new ArrayList<Article>();	
		try {
			String StrSql = "SELECT * FROM T_Articles";
			ResultSet resultSet = connexion.executeQuery(StrSql);
			while(resultSet.next()) {
				int rsIdUser = resultSet.getInt(1);
				String rsDescription = resultSet.getString(2);
				String rsBrand = resultSet.getString(3);
				double rsPrice = resultSet.getDouble(4);
				articles.add((new Article(rsIdUser,rsDescription,rsBrand,rsPrice)));
			}
//			for(Article a : articles)
//				System.out.println(a);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return (ArrayList<T>) articles;
	}
}
