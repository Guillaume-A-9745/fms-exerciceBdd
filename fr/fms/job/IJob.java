package fr.fms.job;

import fr.fms.entities.ShoppingCart;

public interface IJob {
	public boolean verifieUserConnect(String login , String psw);	//Verifier le login et psw
	public void readAllArticles(); 									//Afficher la liste d'article
	public void createCart(String login, String psw);//verifier si un panier existe + creer un panier 
	//Ajouter un article au panier
	//supprimer un article du panier
	public ShoppingCart readCart(String login, String psw);											//Afficher le contenu du panier
	//Supprimer tout le panier
	//Valider la commande
	
	
	//consulter les commandes antérieurs
}
