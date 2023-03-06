package fr.fms.entities;

public class Order_Articles {
	
	int idCart;
	int idArticle;
	int quantity;
	
	String description;
	String brand;
	double price;
	
	public Order_Articles(int idCart,int idArticle, int quantity) {
		this.idCart = idCart;
		this.idArticle = idArticle;
		this.quantity = quantity;
	}
	
	public Order_Articles(int idArticle, String description,String brand,double price, int quantity) {
		this.idArticle = idArticle;
		this.description = description;
		this.brand = brand;
		this.price = price;
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
		return  "  -->Id : " + idArticle + ", Produit : " + description  + ", Marque : " + brand + ", Prix unitaire : " + price +"€ , quantité : " + quantity;
	}
}
