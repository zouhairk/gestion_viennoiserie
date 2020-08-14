package com.ibformation.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ibformation.app.bo.Article;
import com.ibformation.app.bo.Participant;

public class DaoParticipantImp implements DaoParticipant {

	@Override
	public Optional<Participant> find(Long id) throws SQLException {
		List<Participant> participants = new ArrayList<>();
		// 1 - Connexion à la base
		Connection cnx = DAOUtil.getConnexion();
		// 2 - Préparation de la requete
		// fabrication requete
		String requete = "SELECT * FROM participant p where p.id=?";
		try {
			PreparedStatement pstmt = cnx.prepareStatement(requete);
			pstmt.setLong(1, id);
			// 3 - execute la requete
			ResultSet rs = pstmt.executeQuery(requete); // CAR ON FAIT UN SELECT

			// tant que j'ai des lignes de résultats
			while (rs.next()) {
				Participant participant = new Participant();
				participant.setNom(rs.getString("nom"));
				participant.setPrenom(rs.getString("prenom"));
				participants.add(participant);
			}

			cnx.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Optional.of(participants.get(0));
	}

	@Override
	public List<Participant> findAll() throws SQLException {
		List<Participant> participants = new ArrayList<>();
		// 1 - Connexion à la base
		Connection cnx = DAOUtil.getConnexion();
		// 2 - Préparation de la requete
		// fabrication requete
		String requete = "SELECT * FROM article a , article_participant ap, participant p "
				+ "where    ap.id_user = p.id AND a.id = ap.id_article";
		try {
			PreparedStatement pstmt = cnx.prepareStatement(requete);
			// 3 - execute la requete
			ResultSet rs = pstmt.executeQuery(); // CAR ON FAIT UN SELECT
			// tant que j'ai des lignes de résultats
			while (rs.next()) {
				Participant participant = new Participant();
				participant.setNom(rs.getString("nom"));
				participant.setPrenom(rs.getString("prenom"));
				participant.setId(Long.valueOf(rs.getInt("p.id")));
				/*
				 * Construire les articles a partir de la base
				 */
				Article a = new Article();
				List<Article> articles = new ArrayList<Article>();
				a.setDateProgrammee(rs.getTimestamp("prochaine_date").toLocalDateTime());
				a.setId(rs.getInt("a.id"));
				a.setNomArticle(rs.getString("a.nom_article"));
				a.setQuantite(rs.getInt("ap.quantite"));
				/*
				 * Ajouter une article
				 */
				articles.add(a);

				/*
				 * Affecter à l'utilisateur
				 */

				participant.setArticle(articles);

				participants.add(participant);
			}

			cnx.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return participants;
	}

	@Override
	public boolean save(Participant o, Article a) throws SQLException {
		int r = 2;
		try {
			// 1 - Connexion à la base
			Connection cnx = DAOUtil.getConnexion();

			// 2 - Préparation de la requete
			// fabrication requete
			String requete = "INSERT INTO participant(id, nom,prenom) VALUES(null, ?, ?);";
			PreparedStatement pstmt = cnx.prepareStatement(requete);
			// insérer les paramètres
			pstmt.setString(1, o.getNom());
			pstmt.setString(2, o.getPrenom());
			// 3 - execute la requete
			r = pstmt.executeUpdate(); // car on fait pas un select
			String requete2 = "INSERT INTO article_participant(id_user, id_article, prochaine_date, quantite) VALUES (LAST_INSERT_ID(), ?, ?, ?)";
			PreparedStatement pstmt2 = cnx.prepareStatement(requete2);

			pstmt2.setInt(1, a.getId());
			pstmt2.setTimestamp(2, Timestamp.valueOf(a.getDateProgrammee()));
			pstmt2.setInt(3, a.getQuantite());
			r = pstmt2.executeUpdate(); // car on fait pas un select

			// 4 - fermer la connexion
			cnx.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return (r == 2) ? false : true;
	}

	@Override
	public boolean update(Participant o) throws SQLException {
		int r = 2;
		try {
			// 1 - Connexion à la base
			Connection cnx = DAOUtil.getConnexion();

			// 2 - Préparation de la requete
			// fabrication requete
			String requete = "UPDATE participant SET nom=?, prenom=? WHERE id=?";
			PreparedStatement pstmt = cnx.prepareStatement(requete);
			// insérer les paramètres

			pstmt.setString(1, o.getNom());
			pstmt.setString(2, o.getPrenom());
			pstmt.setInt(3, Integer.valueOf(o.getId() + ""));

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
	public boolean delete(Participant o) throws SQLException {
		int r = 2;
		try {
			// 1 - Connexion à la base
			Connection cnx = DAOUtil.getConnexion();

			// 2 - Préparation de la requete
			// fabrication requete
			String requete = "DELETE FROM participant WHERE id=? ;";
			PreparedStatement pstmt = cnx.prepareStatement(requete);
			// insérer les paramètres
			pstmt.setInt(1, Integer.valueOf(o.getId() + ""));

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
	public boolean save(Participant o) throws SQLException {
		int r = 2;
		try {
			// 1 - Connexion à la base
			Connection cnx = DAOUtil.getConnexion();

			// 2 - Préparation de la requete
			// fabrication requete
			String requete = "INSERT INTO participant(nom,prenom) VALUES(?, ?);";

			PreparedStatement pstmt = cnx.prepareStatement(requete);
			// insérer les paramètres
			pstmt.setString(1, o.getNom());
			pstmt.setString(2, o.getPrenom());

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
	public Optional<Participant> chercherParNom(String afficherInserer) {
		// TODO Auto-generated method stub
		return null;
	}

}
