package com.ibformation.app.bo;

import java.util.List;

public class Participant {

	private Long id;
	private String nom;
	private String prenom;

	private List<Article> article;

	public Participant() {
		// TODO Auto-generated constructor stub
	}

	public Participant(Long id, String nom, String prenom) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public List<Article> getArticle() {
		return article;
	}

	public void setArticle(List<Article> article) {
		this.article = article;
	}

	@Override
	public String toString() {
		return "Participant [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", article=" + article + "]";
	}

}
