package fr.fms.entities;

public class ShoppingCart_Articles {
	int idCart;
	int idArticle;
	int quantity;
	
	public ShoppingCart_Articles(int idCart,int idArticle, int quantity) {
		this.idCart = idCart;
		this.idArticle = idArticle;
		this.quantity = quantity;
	}
	
	public int getIdCart() {
		return idCart;
	}
	public int getIdArticles() {
		return idArticle;
	}
	public int getQuantity() {
		return quantity;
	}

	public String toString() {
		return  "  --Article n° " + idArticle + ", quantité : " + quantity;
	}
}
