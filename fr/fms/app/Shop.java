package fr.fms.app;

import java.util.ArrayList;
import java.util.Scanner;

import fr.fms.dao.ArticleDao;
import fr.fms.dao.UserDao;
import fr.fms.entities.Article;
import fr.fms.entities.User;
import fr.fms.job.IJobImpl;

public class Shop {
	static IJobImpl act = new IJobImpl();

	public static void main(String[] args) throws Exception {
		ArticleDao<Article> articles = new ArticleDao<Article>();	
		//articles.create(new Article("Batterie TopTop", "Syno", 80));
		//System.out.println(articles.read(11));
		//articles.update(new Article(17, "Batterie TopTop", "Syno", 150));
		//articles.delete(new Article(19, "Batterie TopTop", "Syno", 180));
		//ArrayList<Article> art = articles.readAll();
		//for(Article a : art)
		//	System.out.println(a);

		UserDao<User> users = new UserDao<User>();	
		//users.create(new User("Jean","20005"));
		//System.out.println(users.read(2));
		//users.update(new User(5,"Jeanne","20005"));
		//users.delete(new User(3,"",""));
		ArrayList<User> user = users.readAll();
		for(User u : user)
			System.out.println(u);
		// Exercice 10

		Scanner scan = new Scanner(System.in);
		String userLogin;
		boolean userConnect = false;
		while(true) {
			try {
				System.out.println("Indiquez votre login :");
				userLogin = scan.next();
				//if(act.VerifieUser(userLogin)) System.out.println("User ok");
				System.out.println("Indiquez votre not de passe :");
				String userPsw = scan.next();
				userConnect = act.VerifiePsw(userLogin, userPsw);
				System.out.println(userConnect);
				if(!userConnect) {
					throw new RuntimeException("Login ou mot de passe invalide !");
				} else {
					//System.out.println("Password ok");		
					ArrayList<Article> article = articles.readAll();
					for(Article a : article)
						System.out.println(a);		//Affichage de la liste des articles
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());	
			}
		}
	}
}
