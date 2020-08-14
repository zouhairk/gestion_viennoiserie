package com.ibformation.app.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAOUtil {

	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String PORT = "3306";
	private static final String DATABASE_NAME = "viennoiseries";
	private static final String LINK = "jdbc:mysql://localhost:" + PORT + "/" + DATABASE_NAME
			+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static final String USER = "root";
	private static final String PASSWORD = "root";

	public static Connection getConnexion() {
		Connection connection = null;
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(LINK, USER, PASSWORD);
		} catch (Exception e) {
			System.out.println(e);
		}
		return connection;
	}

}
