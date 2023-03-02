package fr.fms.dao;

import java.util.ArrayList;

public interface Dao<T> {

	public CreateConfFile connexion = CreateConfFile.getInstance();
	public void create(T obj); 			//Ajout	
	public T read(int id);				//Lire
	public boolean update(T obj);		//Mettre à jour
	public boolean delete(T obj);		//Supprimer
	public ArrayList<T> readAll();		//Lire toute une table
}

