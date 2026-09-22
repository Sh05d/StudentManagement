package app.studentmanagement.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static final String DB_URL =
	        "jdbc:sqlserver://localhost:1433;"
	        + "databaseName=student_management;"
	        + "integratedSecurity=true;"
	        + "encrypt=false";

	public static Connection getConnection() throws SQLException {
		// Establish connection
		Connection connection = DriverManager.getConnection(DB_URL);
		return connection;
	}
}
