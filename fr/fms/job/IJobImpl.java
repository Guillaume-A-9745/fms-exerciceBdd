package fr.fms.job;

import java.util.ArrayList;

import fr.fms.dao.ArticleDao;
import fr.fms.dao.OrderDao;
import fr.fms.dao.Order_ArticlesDao;
import fr.fms.dao.ShoppingCartDao;
import fr.fms.dao.ShoppingCart_ArticlesDao;
import fr.fms.dao.UserDao;
import fr.fms.entities.Article;
import fr.fms.entities.Order;
import fr.fms.entities.Order_Articles;
import fr.fms.entities.ShoppingCart;
import fr.fms.entities.ShoppingCart_Articles;
import fr.fms.entities.User;

public class IJobImpl implements IJob{
	ArticleDao<Article> articles = new ArticleDao<Article>();
	UserDao<User> user = new UserDao<>();
	ShoppingCartDao<ShoppingCart> cart = new ShoppingCartDao<ShoppingCart>();
	ShoppingCart_ArticlesDao<ShoppingCart_Articles> article = new ShoppingCart_ArticlesDao<ShoppingCart_Articles>();
	OrderDao<Order> order = new OrderDao<Order>();
	Order_ArticlesDao<Order_Articles> orderArticle = new Order_ArticlesDao<Order_Articles>();
	
	@Override
	public boolean verifieUserConnect(String login , String psw){
		if(!user.verifiePsw(login, psw)) {		
			throw new RuntimeException("Login ou mot de passe invalide !");
		}
		//		else {
		//			ArrayList<Article> article = articles.readAll();
		//			for(Article a : article)
		//				System.out.println(a);		//Affichage de la liste des articles
		//		}
		return true;
	}
	@Override
	public void readAllArticles() {
		ArrayList<Article> article = articles.readAll();
		for(Article a : article)
			System.out.println(a);		//Affichage de la liste des articles
	}

	public int retrieveIdConnected(String login, String psw) { 
		int connetedId = user.retrieveId(login,psw);
		return connetedId;
	}
	@Override
	public void createCart(String login, String psw) {
		int connetedId = user.retrieveId(login,psw);
		if(cart.read(connetedId) == null) {
			cart.create(new ShoppingCart(connetedId));
		}
	}
	@Override
	public ShoppingCart readCart(String login, String psw) {
		int connetedId = user.retrieveId(login,psw);
		if(cart.read(connetedId) == null) {
			createCart(login,psw);
		}
		ShoppingCart userCart = cart.readCart(connetedId);
		return userCart;
	}
	public ShoppingCart_Articles readCart_Article(String login, String psw) {
		int connetedId = user.retrieveId(login,psw);
		if(cart.read(connetedId) == null) {
			createCart(login,psw);
		}
		ShoppingCart userCart = cart.read(connetedId);
		ShoppingCart_Articles userArticles = article.read(userCart.getIdCart());
		return userArticles;
	}
	@Override
	public void addArticles(String login, String psw, int idArticle, int quantity) {
		int connetedId = user.retrieveId(login,psw);
		if(cart.read(connetedId) == null) {
			createCart(login,psw);
		}
		ShoppingCart userCart = cart.read(connetedId);
		article.create(new ShoppingCart_Articles(userCart.getIdCart(),idArticle,quantity));
	}
	@Override
	public void deletearticle(int idArticle) {
		article.deleteArticle(idArticle);
	}
	@Override
	public void deleteAllArticle(String login, String psw) {
		int connetedId = user.retrieveId(login,psw);
		ShoppingCart userCart = cart.read(connetedId);
		article.deleteAllArticle(userCart.getIdCart());	
	}
	@Override
	public void valideCart(String login, String psw) {
	
		
	}
}
