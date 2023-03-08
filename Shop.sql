-- 
-- - Reconstruction de la base de donnée -
--
DROP DATABASE if EXISTS Shop;
CREATE DATABASE Shop;
USE Shop;

--
-- - Construction de la table des articles en ventes -
--
CREATE TABLE T_Articles (
	IdArticle 	int(4) 		PRIMARY KEY AUTO_INCREMENT,
	Description varchar(30) NOT NULL,
	Brand 		varchar(30) NOT NULL,
	UnitaryPrice float(8)   NOT NULL
)ENGINE = InnoDB;

INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('Souris', 'Logitoch', 65);
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('Clavier', 'Microhard', 49.5);
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('Systeme d exploitation', 'Fenetres Vistouille', 150);
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('Tapis de souris', 'Chapeau bleu', 5);
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('Clé USB 8 To', 'Syno', 8);
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('Laptop', 'PH', 1199);
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('CD x 500', 'CETME', 250);
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('DVD-R x 100', 'CETME', 99);
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('DVD+R x 100', 'CETME', 105);
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('Batterie Laptop', 'PH', 80);
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('Casque Audio', 'Syno', 105);
INSERT INTO T_Articles (Description, Brand, UnitaryPrice) VALUES ('Webcam', 'Logitoch', 755);

SELECT * FROM T_Articles;



--
-- - Construction de la table des catégorie -
--
CREATE TABLE T_Category (
	IdCategory 	int(4) 		PRIMARY KEY AUTO_INCREMENT,
	Name 		varchar(30) NOT NULL,
	Description varchar(30) NOT NULL
)ENGINE = InnoDB;

INSERT INTO T_Category (Name, Description) VALUES ('Matériel info', 'Indispensables à un pc');
INSERT INTO T_Category (Name, Description) VALUES ('Logiciel', 'SE, Antivir, Ide...');
INSERT INTO T_Category (Name, Description) VALUES ('PC', 'Laptop et micro ordi...');


SELECT * FROM T_Category;


--
-- - modifier le table des articles
--

ALTER TABLE T_Articles ADD COLUMN IdCategory int(4);
ALTER TABLE T_Articles ADD FOREIGN KEY(IdCategory) REFERENCES T_Category(IdCategory) ;


--
-- - Mettre à jour les articles
--

update T_Articles set IdCategory=1 where IdArticle=1;
update T_Articles set IdCategory=1 where IdArticle=2;
update T_Articles set IdCategory=2 where IdArticle=3;
update T_Articles set IdCategory=1 where IdArticle=4;
update T_Articles set IdCategory=1 where IdArticle=5;
update T_Articles set IdCategory=3 where IdArticle=6;
update T_Articles set IdCategory=1 where IdArticle=7;
update T_Articles set IdCategory=1 where IdArticle=8;
update T_Articles set IdCategory=1 where IdArticle=9;
update T_Articles set IdCategory=1 where IdArticle=10;
update T_Articles set IdCategory=1 where IdArticle=11;
update T_Articles set IdCategory=1 where IdArticle=12;


----------------------------------------------------------
--         -------------- ExosBdd -----------------
----------------------------------------------------------

-- 1.1 fait

-- 1.2 
show tables;

-- 1.3
describe T_Articles;
describe T_Category;

-- 1.4
INSERT INTO T_Articles (Description, Brand, UnitaryPrice, IdCategory) VALUES ('MacBook', 'Apple', 2000, 3);
INSERT INTO T_Articles (Description, Brand, UnitaryPrice, IdCategory) VALUES ('Office', 'Microsoft', 150, 2);

-- 1.5
update T_Articles set Brand='Logitech', UnitaryPrice=85 where IdArticle=1;

-- 1.6
delete from T_Articles where IdArticle=2;

-- 1.7
select * from T_Articles where UnitaryPrice > 100;

-- 1.8
select * from T_Articles where UnitaryPrice > 50 and UnitaryPrice < 100;
-- or
select * from T_Articles where UnitaryPrice between 50 and  100;

-- 1.9
select * from T_Articles order by UnitaryPrice ;
-- decroissant
select * from T_Articles order by UnitaryPrice desc;
-- 1.10
select T_Articles.Description from T_Articles;

-- 1.11
select IdArticle,T_Articles.Description,UnitaryPrice,Name from T_Articles inner join T_Category on T_Articles.IdCategory = T_Category.IDCategory where UnitaryPrice > 500;
-- 1.12 fait plus haut

-- 1.13 
select IdArticle,T_Articles.Description,UnitaryPrice,Name from T_Articles inner join T_Category on T_Articles.IdCategory = T_Category.IDCategory order by UnitaryPrice ;


-- 2


-- 6
CREATE TABLE T_Users ( 
	IdUser int (4) PRIMARY KEY AUTO_INCREMENT, 
	Login varchar (20) NOT NULL,
	Password varchar (20) NOT NULL
) ENGINE = InnoDB;

-- 7
INSERT INTO T_Users (Login, Password) VALUES ('Martin', '20001');
INSERT INTO T_Users (Login, Password) VALUES ('Julie', '20002');
INSERT INTO T_Users (Login, Password) VALUES ('Alexander', '20003');
INSERT INTO T_Users (Login, Password) VALUES ('Marie', '20004');

-- 11
CREATE USER 'Guillaume'@'localHost' IDENTIFIED BY 'Wing*993';
GRANT ALL PRIVILEGES ON shop.* TO 'Guillaume'@'localHost';
FLUSH PRIVILEGES;
mysql -u Guillaume -p
show databases;
show GRANTS FOR 'Guillaume'@'localhost';
-- Drop user 'Guillaume'@'localhost';  --pour supprimer 


-- 12
--
-- - Construction de la table panier -
--
CREATE TABLE T_ShoppingCart (
	IdCart	 	int(4) 		PRIMARY KEY AUTO_INCREMENT,
	IdUser 		int(4) 		NOT NULL,
	Date  		DATE,
	FOREIGN KEY (IdUser) REFERENCES T_Users(IdUser)
)ENGINE = InnoDB;


CREATE TABLE T_ShoppingCart_Articles (
	IdShCaArt  	int(4)   	PRIMARY KEY AUTO_INCREMENT, 
	IdCart	 	int(4) 		NOT NULL,
	IdArticle 	int(4) 		NOT NULL,
	Quantity 	int(4)		NOT NULL,
	FOREIGN KEY (IdCart) REFERENCES T_ShoppingCart(IdCart),
	FOREIGN KEY (IdArticle) REFERENCES T_Articles(IdArticle)
)ENGINE = InnoDB;

 

--
-- - Construction de la table commande -
--
CREATE TABLE T_Order (
	IdOrder 	int(4) 		PRIMARY KEY AUTO_INCREMENT,
	IdUser 		int(4) 		NOT NULL,
	Date 		DATE,
	FOREIGN KEY (IdUser) REFERENCES T_Users(IdUser)
)ENGINE = InnoDB;

CREATE TABLE T_Order_Articles (
	IdOrArt   	int(4)     	PRIMARY KEY AUTO_INCREMENT, 
	IdOrder  	int(4) 		NOT NULL,
	IdArticle 	int(4) 		NOT NULL,
	Quantity 	int(4)		NOT NULL,
	FOREIGN KEY (IdOrder) REFERENCES T_Order(IdOrder),
	FOREIGN KEY (IdArticle) REFERENCES T_Articles(IdArticle)
)ENGINE = InnoDB;