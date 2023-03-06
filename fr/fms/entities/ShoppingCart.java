package fr.fms.entities;

import java.time.LocalDate;

public class ShoppingCart {
	
	int idCart;
	int idUser;
	static LocalDate date;
	
	public ShoppingCart(int idCart, int idUser ) {
		this.idCart = idCart;
		this.idUser = idUser;
		this.date = LocalDate.now();
	}
	
	public ShoppingCart(int idUser ) {
		this.idUser = idUser;
		this.date = LocalDate.now();
	}

	public int getIdCart() {
		return idCart;
	}

	public int getIdUser() {
		return idUser;
	}

	public static LocalDate getDate() {
		return date;
	}	
	
	public String toString() {
		return "Panier n° " + idCart + "\nDe : " +idUser + "\nDate de création : " + date;
	}
}
