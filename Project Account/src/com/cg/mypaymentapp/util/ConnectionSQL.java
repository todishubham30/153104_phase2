package com.cg.mypaymentapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSQL {
	
	public static Connection connection;
	static {
	try {
		Class.forName("com.mysql.jdbc.Driver");
	}
	catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
	
	try {
		connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/myPaymentApp","root","corp123");
	}
	catch (SQLException e){
	e.printStackTrace();
	}
}
}
