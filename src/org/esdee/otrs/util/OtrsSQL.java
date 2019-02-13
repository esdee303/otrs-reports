package org.esdee.otrs.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sun.rowset.CachedRowSetImpl;

public class OtrsSQL {
	private static Connection conn = null;
	static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
	static final String FILE = "c:\\eclipse-cred\\otrs.txt";
	public static void dbConnect() throws SQLException, ClassNotFoundException, FileNotFoundException, IOException {
		String line;
		String ipAddress= "";
		String username = "";
		String password = "";
		BufferedReader br = new BufferedReader(new FileReader(FILE));
		while ((line = br.readLine()) != null) {
			if (line.contains("ipaddress")) {
				ipAddress = line.substring(line.indexOf("=") + 1, line.length());
			} else if (line.contains("username")) {
				username = line.substring(line.indexOf("=") + 1, line.length());
			} else if (line.contains("password")) {
				password = line.substring(line.indexOf("=") + 1, line.length());
			}
		}
		br.close();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your JDBC driver");
			e.printStackTrace();
			throw e;
		}

		try {
			conn = DriverManager.getConnection("jdbc:mysql://" + ipAddress + "/otrs?useSSL=false", username, password);
		} catch (SQLException e) {
			System.out.println("Connection failed ! Check output console");
			e.printStackTrace();
			throw e;
		}
	}

	// Close the resultSet
	public static void dbDisconnect() throws SQLException {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (Exception e) {
			throw e;
		}
	}

	// DB Execute Query Operation
	public static ResultSet dbExecuteQuery(String queryStatement) throws SQLException, ClassNotFoundException, FileNotFoundException, IOException {
		Statement statement = null;
		ResultSet resultSet = null;
		CachedRowSetImpl crs = null;
		try {
			// Connect to DB
			dbConnect();

			// Create statement
			statement = conn.createStatement();

			// Execute select (query) operation
			resultSet = statement.executeQuery(queryStatement);

			// In order to prevent "java.sql.SQLRecoverableException: Closed Connection:
			// next" error,
			// we use CachedRowSet

			crs = new CachedRowSetImpl();
			crs.setMaxRows(1);
			crs.setPageSize(1);
			crs.populate(resultSet);
		} catch (SQLException e) {
			System.out.println("Problem occured at executeQuery operation: " + e);
			throw e;
		} finally {
			if (resultSet != null) {
				// Close resultSet
				resultSet.close();
			}
			if (statement != null) {
				// Close statement
				statement.close();
			}
			// Close DB connection
			dbDisconnect();
		}

		// return CachedRowSet
		return crs;
	}

	// DB Execute Update (For Update/Insert/Delete) Operation
	public static void dbExecuteUpdate(String sqlStatement) throws SQLException, ClassNotFoundException, FileNotFoundException, IOException {
		// Declare statement as null
		Statement statement = null;
		try {
			// Connect to DB
			dbConnect();

			// Create statement
			statement = conn.createStatement();

			// Run executeUpdate operation with given sql statement
			statement.executeUpdate(sqlStatement);
		} catch (SQLException e) {
			System.out.println("Problem occured at executeUpdate operation : " + e);
			throw e;
		} finally {
			if (statement != null) {
				// Close statement
				statement.close();
			}
			// Close connection
			dbDisconnect();
		}
	}
}

