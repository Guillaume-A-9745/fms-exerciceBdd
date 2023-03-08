package fr.fms.job;

import fr.fms.entities.ShoppingCart;
import fr.fms.entities.ShoppingCart_Articles;

public interface IJob {
	public boolean verifieUserConnect(String login , String psw);						//Verifier le login et psw
	public void createCart(String login, String psw);									//verifier si un panier existe + creer un panier 
	public void readAllArticles(); 														//Afficher la liste d'article
	public void addArticle(String login, String psw, int idArticle, int Quantity); 	//Ajouter un article au panier
	public void deletearticle(int idArticle);											//supprimer un article du panier
	public ShoppingCart readCart(String login, String psw);								//Afficher le contenu du panier
	public void deleteAllArticle(String userLogin, String userPsw);						//Supprimer tout le panier
	
	
	public void valideCart(String login, String psw);									//Valider la commande TODO
	
	
	//consulter les commandes antérieurs
}
