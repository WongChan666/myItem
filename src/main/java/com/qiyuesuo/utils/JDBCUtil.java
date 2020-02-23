package com.qiyuesuo.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 *Administrator
 *2020��2��22��
 *jdbc����derby�Ĺ�����
*/
public class JDBCUtil {
	// ���ݿ�·��url
	private final static String DB_URL = "jdbc:derby:E:\\DeverlopmentTool\\derby\\database\\firstdb;";
	// Driver
	private final static String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	// user
	private final static String USER = "root";
	// password
	private final static String PASSWORD = "root";

	// ��ȡconnection
	public static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName(DRIVER);
			try {
				connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}

	// ��ȡPreparedStatement
	public static PreparedStatement getPreparedStatement(String sql, Connection connection) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return preparedStatement;
	}

	// ��ȡResultSet
	public static ResultSet getResultSet(PreparedStatement pStatement) {
		ResultSet rSet = null;
		try {
			rSet = pStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rSet;
	}
	
	
	//��Դ�ر�
	public static void close(Connection connection,PreparedStatement pStatement,ResultSet rSet){
		try {
			rSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			pStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//
	public static void close(Connection connection,PreparedStatement pStatement){
		try {
			pStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
