package fr.fms.app;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import fr.fms.dao.ArticleDao;
import fr.fms.dao.UserDao;
import fr.fms.entities.Article;
import fr.fms.entities.User;
import fr.fms.job.IJobImpl;

public class Shop {
	static IJobImpl act = new IJobImpl();
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m	";

	public static void main(String[] args) {
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
		boolean userConnect = false;
		while(!userConnect) {
			try {
				//Connection
				System.out.println("Indiquez votre login :");
				String userLogin = scan.next();
				System.out.println("Indiquez votre not de passe :");
				String userPsw = scan.next();
				userConnect = act.verifieUserConnect(userLogin, userPsw);
				System.out.println(userConnect);
				//Affichage du menu
				System.out.println("\nBonjour " + userLogin);
				System.out.println("Que desire vous faire ?");
				while (userConnect) {
					System.out.print(ANSI_BLUE);
					System.out.println("Menu : ");
					System.out.println("1 --> Afficher la liste des articles." + "\t\t2 --> Ajouter un article à mon panier");
					System.out.println("3 --> Afficher le contenu de mon panier." + "\t4 --> Supprimer un article à mon panier");
					System.out.println("5 --> Valider mon panier." + "\t\t\t6 --> Supprimer tous mon panier");
					System.out.println("7 --> Consulter mes commandes antérieurs." +ANSI_RED+ "\t\t8 --> Quitter l'application"+ANSI_RESET);
					while(!scan.hasNextInt()) scan.next();
					int choice = scan.nextInt();
					scan.nextLine();
					switch (choice) {
					case 1:
						act.readAllArticles();
						break;
					case 2:
						System.out.println( java.sql.Date.valueOf(LocalDate.now()));
						break;
					case 3:
						System.out.println(act.readCart(userLogin, userPsw));
						break;
					case 4:

						break;
					case 5:

						break;
					case 6:

						break;
					case 7:

						break;
					case 8:
						System.out.println(ANSI_RED);
						System.out.println("Fin de la transaction.");
						System.out.print(ANSI_RESET);
						userConnect = false;
						break;

					default:
						System.out.println(ANSI_RED);
						System.out.println("Choix invalide.");
						System.out.print(ANSI_RESET);
						break;
					}
				}

			} catch (Exception e) {
				System.out.println(e.getMessage());	
			}
		}


		scan.close();
	}
}
