package com.ibformation.app.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.ibformation.app.bo.Article;
import com.ibformation.app.bo.Participant;
import com.ibformation.app.dao.Dao;
import com.ibformation.app.dao.DaoParticipantImp;

public class ServiceParticipantImp implements ServiceParticipant {

	private Dao<Participant, Article, Long> daoParticipant = new DaoParticipantImp();

	public ServiceParticipantImp() {
	}

	@Override
	public Optional<Participant> find(Long id) throws SQLException {

		return daoParticipant.find(id);
	}

	@Override
	public List<Participant> findAll() throws SQLException {
		return daoParticipant.findAll();
	}

	@Override
	public boolean save(Participant o) throws SQLException {
		return daoParticipant.save(o);
	}

	@Override
	public boolean update(Participant o) throws SQLException {
		return daoParticipant.update(o);
	}

	@Override
	public boolean delete(Participant o) throws SQLException {
		return daoParticipant.delete(o);
	}

	@Override
	public boolean save(Participant o, Article a) throws SQLException {
		return daoParticipant.save(o, a);
	}

	@Override
	public Optional<Participant> chercherParNom(String afficherInserer) throws SQLException {
		return daoParticipant.chercherParNom(afficherInserer);
	}
}
