package fr.fms.entities;

public class User {

	int id;
	String login;
	String password;
	
	public User(int id,String login,String password) {
		this.id = id;
		this.login = login;
		this.password = password;
	}
	public User(String login,String password) {
		this.login = login;
		this.password = password;
	}
	
	public int getId() {
		return id;
	}
	public String getLogin() {
		return login;
	}
	public String getPassword() {
		return password;
	}
	
	public String toString() {
		return "Id = " + id + ", Login = " + login + ", password = " + password;
	}
}
