package fr.fms.job;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import fr.fms.dao.ArticleDao;
import fr.fms.dao.ShoppingCartDao;
import fr.fms.dao.UserDao;
import fr.fms.entities.Article;
import fr.fms.entities.ShoppingCart;
import fr.fms.entities.User;

public class IJobImpl implements IJob{
	ArticleDao<Article> articles = new ArticleDao<Article>();
	UserDao<User> user = new UserDao<>();
	ShoppingCartDao<ShoppingCart> cart = new ShoppingCartDao<ShoppingCart>();

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
		ShoppingCart userCart = cart.read(connetedId);
		return userCart;
	}
}
