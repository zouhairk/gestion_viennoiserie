package com.ibformation.app.bo;

import java.time.LocalDateTime;

public class Article {

	private int id;

	private String nomArticle;

	private int quantite;

	private LocalDateTime dateProgrammee;

	public Article() {
		// TODO Auto-generated constructor stub
	}

	public Article(String nomArticle, int quantite) {
		super();
		this.nomArticle = nomArticle;
		this.quantite = quantite;
	}

	public String getNomArticle() {
		return nomArticle;
	}

	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public LocalDateTime getDateProgrammee() {
		return dateProgrammee;
	}

	public void setDateProgrammee(LocalDateTime dateProgrammee) {
		this.dateProgrammee = dateProgrammee;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", nomArticle=" + nomArticle + ", quantite=" + quantite + ", dateProgrammee="
				+ dateProgrammee + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nomArticle == null) ? 0 : nomArticle.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Article other = (Article) obj;
		if (nomArticle == null) {
			if (other.nomArticle != null)
				return false;
			/*
			 * On change la méthode equals to conains pour faire la recherche et pas
			 * exactement ce que on écrit
			 */
		} else if (!nomArticle.contains(other.nomArticle))
			return false;
		return true;
	}

}
