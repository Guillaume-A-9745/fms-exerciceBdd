package fr.fms.entities;

import java.time.LocalDate;

public class Order {
	int idCart;
	int idUser;
	String login;
	static LocalDate date;
	
	public Order(int idCart, int idUser ) {
		this.idCart = idCart;
		this.idUser = idUser;
		this.date = LocalDate.now();
	}
	
	public Order(int idUser ) {
		this.idUser = idUser;
		this.date = LocalDate.now();
	}

	public Order(String login) {
		this.login = login;
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
		
		return "Commande de " + login + "\nDu " + date ;
	}
}
