package fr.fms.dao;

import java.sql.Connection;
import java.util.ArrayList;

public interface Dao<T> {

	//public Connection connection = bddConnection.getConnection();
	public void create(T obj); 			//Ajout	
	public T read(int id);				//Lire
	public boolean update(T obj);		//Mettre à jour
	public boolean delete(T obj);		//Supprimer
	public ArrayList<T> readAll();		//Lire toute une table
}

