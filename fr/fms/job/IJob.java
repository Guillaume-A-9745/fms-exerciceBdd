package fr.fms.job;

import fr.fms.dao.CreateConfFile;

public interface IJob {

	public CreateConfFile connexion = CreateConfFile.getInstance();
	public boolean VerifieUser(String user); 			//Verifier si l'user est en base de donner
	public boolean VerifiePsw(String user, String psw);	//verifie si l'user à un mdp lier au compte en base de donner
	
	//verifier si un panier exicete + creer un panier 
	//Ajouter un article au panier
	//supprimer un article du panier
	//Afficher le contenu du panier
	//Supprimer tout le panier
	//Valider la commande
	
	//consulter les commandes antérieurs
}
