package com.ibformation.app.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.ibformation.app.bo.Article;
import com.ibformation.app.bo.Participant;

public interface Dao<T, Z, ID> {

	Optional<T> find(ID id) throws SQLException;

	List<T> findAll() throws SQLException;

	public boolean save(T o, Z a) throws SQLException;

	public boolean save(T o) throws SQLException;

	boolean update(T o) throws SQLException;

	boolean delete(T o) throws SQLException;
	
	Optional<T> chercherParNom(String afficherInserer) throws SQLException;

}
