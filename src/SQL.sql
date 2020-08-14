CREATE DATABASE IF NOT EXISTS viennoiseries;
USE
  viennoiseries;
CREATE TABLE IF NOT EXISTS article(
  id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  nom_article VARCHAR(100) NOT NULL
);
CREATE TABLE IF NOT EXISTS Participant(
  id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  nom VARCHAR(100) NOT NULL,
  prenom VARCHAR(100) NOT NULL
);
CREATE TABLE IF NOT EXISTS article_participant(
  quantite VARCHAR(20) NOT NULL,
  id_user INT(11) NOT NULL,
  id_article INT(11) NOT NULL,
  prochaine_date TIMESTAMP NOT NULL,
  PRIMARY KEY(
    quantite,
    id_user,
    prochaine_date,
    id_article
  )
);
SELECT
  *
FROM
  article;
SELECT
  *
FROM
  participant;