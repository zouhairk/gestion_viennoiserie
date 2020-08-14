package com.ibformation.app.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.ibformation.app.bo.Article;
import com.ibformation.app.bo.Participant;
import com.ibformation.app.dao.Dao;
import com.ibformation.app.dao.DaoArticleImp;

public class ServiceArticleImp implements ServiceArticle {

	private Dao<Article, Participant, Long> daoArticle = new DaoArticleImp();

	public ServiceArticleImp() {
	}

	@Override
	public Optional<Article> find(Long id) throws SQLException {

		return daoArticle.find(id);
	}

	@Override
	public List<Article> findAll() throws SQLException {
		return daoArticle.findAll();
	}

	@Override
	public boolean save(Article o) throws SQLException {
		return daoArticle.save(o);
	}

	@Override
	public boolean update(Article o) throws SQLException {
		return daoArticle.update(o);
	}

	@Override
	public boolean delete(Article o) throws SQLException {
		return daoArticle.delete(o);
	}

	@Override
	public boolean save(Article o, Participant a) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public Optional<Article> chercherParNom(String afficherInserer) throws SQLException {
		return daoArticle.chercherParNom(afficherInserer);
	}

}
