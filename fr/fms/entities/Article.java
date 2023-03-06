package fr.fms.entities;

public class Article {
	int id;
	String description;
	String brand;
	double price;

	public Article(int id, String description,String brand,double price) {
		this.id = id;
		this.description = description;
		this.brand = brand;
		this.price = price;
	}
	public Article(String description,String brand,double price) {
		this.description = description;
		this.brand = brand;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public String getBrand() {
		return brand;
	}

	public double getPrice() {
		return price;
	}

	public String toString() {
		return "Article [id = " + id + ", Name = " + description + ", Brand = " + brand + ", Price = " + price + "€]";
	}
	
}
