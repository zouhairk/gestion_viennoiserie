package com.ibformation.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ibformation.app.bo.Article;
import com.ibformation.app.bo.Participant;

public class DaoArticleImp implements DaoArticle {

	@Override
	public Optional<Article> find(Long id) throws SQLException {
		List<Article> articles = new ArrayList<>();
		// 1 - Connexion à la base
		Connection cnx = DAOUtil.getConnexion();
		// 2 - Préparation de la requete
		// fabrication requete
		String requete = "SELECT * FROM article l where l.id=?";
		try {
			PreparedStatement pstmt = cnx.prepareStatement(requete);
			pstmt.setLong(1, id);
			// 3 - execute la requete
			ResultSet rs = pstmt.executeQuery(requete); // CAR ON FAIT UN SELECT

			// tant que j'ai des lignes de résultats
			while (rs.next()) {
				Article article = new Article();
				article.setNomArticle(rs.getString("nom_article"));
				articles.add(article);
			}

			cnx.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Optional.of(articles.get(0));
	}

	@Override
	public List<Article> findAll() throws SQLException {
		List<Article> articles = new ArrayList<>();
		// 1 - Connexion à la base
		Connection cnx = DAOUtil.getConnexion();
		// 2 - Préparation de la requete
		// fabrication requete
		/*
		 * String requete =
		 * "SELECT * FROM article a  , article_participant ap, participant p " +
		 * "where    ap.id_user = p.id AND a.id = ap.id_article";
		 */
		String requete = "SELECT * FROM article a";

		try {
			PreparedStatement pstmt = cnx.prepareStatement(requete);
			// 3 - execute la requete
			ResultSet rs = pstmt.executeQuery(); // CAR ON FAIT UN SELECT
			// tant que j'ai des lignes de résultats
			while (rs.next()) {
				Article article = new Article();
				article.setNomArticle(rs.getString("nom_article"));
				// article.setDateProgrammee(rs.getTimestamp("prochaine_date").toLocalDateTime());
				article.setId(rs.getInt("a.id"));
				articles.add(article);
			}

			cnx.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return articles;
	}

	@Override
	public boolean save(Article o) throws SQLException {
		int r = 2;
		try {
			// 1 - Connexion à la base
			Connection cnx = DAOUtil.getConnexion();

			// 2 - Préparation de la requete
			// fabrication requete
			String requete = "INSERT INTO article(nom_article) VALUES(?);";
			PreparedStatement pstmt = cnx.prepareStatement(requete);
			// insérer les paramètres
			pstmt.setString(1, o.getNomArticle());

			// 3 - execute la requete
			r = pstmt.executeUpdate(); // car on fait pas un select

			// 4 - fermer la connexion
			cnx.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return (r == 2) ? false : true;
	}

	@Override
	public boolean update(Article o) throws SQLException {
		int r = 2;
		try {
			// 1 - Connexion à la base
			Connection cnx = DAOUtil.getConnexion();

			// 2 - Préparation de la requete
			// fabrication requete
			String requete = "UPDATE note SET nom_article=? WHERE id=?";
			PreparedStatement pstmt = cnx.prepareStatement(requete);
			// insérer les paramètres

			pstmt.setString(1, o.getNomArticle());
			pstmt.setInt(2, Integer.valueOf(o.getId() + ""));

			// 3 - execute la requete
			r = pstmt.executeUpdate(); // car on fait pas un select

			// 4 - fermer la connexion
			cnx.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return (r == 2) ? false : true;
	}

	@Override
	public boolean delete(Article o) throws SQLException {
		int r = 2;
		try {
			// 1 - Connexion à la base
			Connection cnx = DAOUtil.getConnexion();

			// 2 - Préparation de la requete
			// fabrication requete
			String requete = "DELETE FROM artcile WHERE id=? ;";
			PreparedStatement pstmt = cnx.prepareStatement(requete);
			// insérer les paramètres
			pstmt.setInt(1, Integer.valueOf(o.getId() + ""));

			// 3 - execute la requete
			r = pstmt.executeUpdate(); // car on fait pas un select

			// 4 - fermer la connexion
			cnx.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return (r == 2) ? false : true;
	}

	@Override
	public boolean save(Article o, Participant a) throws SQLException {
		return false;
	}

	@Override
	public Optional<Article> chercherParNom(String afficherInserer) throws SQLException {
		int index = -1;
		afficherInserer = afficherInserer.trim();

		List<Article> articles = this.findAll();

		index = articles.indexOf(new Article(afficherInserer, 0));

		return Optional.of(articles.get(index));
	}

}
