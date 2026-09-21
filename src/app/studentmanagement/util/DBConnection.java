package app.studentmanagement.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static final String DBURL =
	        "jdbc:sqlserver://localhost:1433;"
	        + "databaseName=student_management;"
	        + "integratedSecurity=true;"
	        + "encrypt=false";

	public static Connection getConnection() throws SQLException {
		// Establish connection
		Connection connection = DriverManager.getConnection(DBURL);
		return connection;
	}
}
