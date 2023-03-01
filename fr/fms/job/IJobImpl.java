package fr.fms.job;

import fr.fms.dao.ArticleDao;
import fr.fms.dao.UserDao;
import fr.fms.entities.Article;
import fr.fms.entities.User;

public class IJobImpl {

	public static void main(String[] args) {
		ArticleDao<Article> job = new ArticleDao<Article>();
		
		//job.create(new Article("Batterie TopTop", "Syno", 80));
		//job.read(11);
		//job.update(new Article(17, "Batterie TopTop", "Syno", 150));
		//job.delete(new Article(19, "Batterie TopTop", "Syno", 180));

		//job.readAll();
		
		
		
		UserDao<User> user = new UserDao<User>();
		
		//user.create(new User("Jean","20005"));
		//user.read(2);
		user.update(new User(5,"Jeanne","20005"));
		//user.delete(new User(3,"",""));
		
		user.readAll();
		
	}
}
