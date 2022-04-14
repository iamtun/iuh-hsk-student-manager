package com.iuh.hsk.connectDB;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectDB {
	public static Connection openConnection() throws Exception {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String connectionUrl = "jdbc:sqlserver://localhost;database=QL_SINHVIEN;";
		String username = "sa";
		String password = "123456";
		Connection conn = DriverManager.getConnection(connectionUrl, username, password);

		return conn;
	}
}
