package com.ibformation.app.ihm;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import com.ibformation.app.bo.Article;
import com.ibformation.app.bo.Participant;
import com.ibformation.app.service.Service;
import com.ibformation.app.service.ServiceArticleImp;
import com.ibformation.app.service.ServiceParticipantImp;

public class Launcher {

	public static void main(String[] args) throws SQLException {

		System.out.println("BienVenu sur la Gestion des Viennoiseries ");
		String choix = "0";

		Article a = new Article();
		Participant p = new Participant();
		Service<Article, Participant, Long> articleServ = new ServiceArticleImp();
		Service<Participant, Article, Long> participantServ = new ServiceParticipantImp();

		afficherLeMenuEtFaireUnChoix(choix, a, p, articleServ, participantServ);

	}

	private static void afficherLeMenuEtFaireUnChoix(String choix, Article a, Participant p,
			Service<Article, Participant, Long> articleServ, Service<Participant, Article, Long> participantServ)
			throws SQLException {
		do {
			System.out.println("==============================================================");
			System.out.println("1- Ajouter un Article                                       ||");
			System.out.println("2- Supprimer un Article                                     || ");
			System.out.println("3- Modifier un Article                                      || ");
			System.out.println("4- Afficher La liste des Articles                           ||");
			System.out.println("5- Ajouter un Participant                                   ||");
			System.out.println("6- Supprimer un Participant                                 ||");
			System.out.println("7- Modifier un Participant                                  ||");
			System.out.println("8- Afficher La liste des Participants                       ||");
			System.out.println("9- Ajouter un Participant et affecter un article existant   ||");
			System.out.println("==============================================================");

			System.out.println("X- Quitez le programme (En Majuscule) ");

			Scanner sc0 = new Scanner(System.in);
			choix = sc0.nextLine();

			switch (choix) {

			case "1":
				a.setNomArticle(afficherInserer("Enter le Nom de l'article "));
				articleServ.save(a);
				break;
			case "2":
				a.setId(Integer.valueOf(afficherInserer("L'dentifiant de l'article a supprimer ")));
				articleServ.delete(a);
				break;
			case "3":
				a.setId(Integer.valueOf(afficherInserer("L'dentifiant de l'article a modifier ")));
				a.setNomArticle(afficherInserer("Enter le nouvel nom de l'article "));
				articleServ.update(a);
				break;
			case "4":
				for (Article a1 : articleServ.findAll())
					System.out.println(a1);
				break;
			case "5":
				p.setNom(afficherInserer("Enter le Nom du participant "));
				p.setPrenom(afficherInserer("Enter le Prenom du participant "));
				participantServ.save(p);
				break;
			case "6":
				p.setId(Long.valueOf(afficherInserer("L'dentifiant du participant a supprimer ")));
				participantServ.delete(p);
				break;
			case "7":
				p.setId(Long.valueOf(afficherInserer("L'dentifiant du participant a modifier ")));
				p.setNom(afficherInserer("Enter le nouvel nom du participant "));
				p.setPrenom(afficherInserer("Enter le nouvel prenom du participant "));
				participantServ.update(p);
				break;
			case "8":
				for (Participant part : participantServ.findAll())
					System.out.println(part);
				break;
			case "9":
				p.setNom(afficherInserer("Enter le Nom du participant "));
				p.setPrenom(afficherInserer("Enter le Prenom du participant "));

				a = articleServ.chercherParNom(afficherInserer("Entrer le nom de l'article")).get();
				/*
				 * Format de la date
				 */
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

				a.setDateProgrammee(
						LocalDateTime.parse(afficherInserer("Entrer la date Heure AAAA/MM/JJ hh:mm"), formatter));

				a.setQuantite(Integer.valueOf(afficherInserer("Entrer la quantité souhaitée")));

				participantServ.save(p, a);
				break;
			default:
				System.out.println("Votre choix ne correspond à aucun choix ");
			}
			Scanner sc1 = new Scanner(System.in);
			choix = sc1.nextLine();

		} while (choix.equals("0") || !choix.equals("X"));
	}

	private static String afficherInserer(String message) {
		System.out.println(message);
		Scanner sc12 = new Scanner(System.in);
		return sc12.nextLine();
	}

}
