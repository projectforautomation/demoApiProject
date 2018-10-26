package com.skilrock.dge.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.skilrock.dge.common.utils.ConfigManager;

public class DBConnection {
	private static Logger logger = LoggerFactory.getLogger(DBConnection.class);
	private static DBConnection dbconnection = null;
	private static Connection lmsConnection = null;

	public static DBConnection getInstance() {
		if (dbconnection == null) {
			synchronized (DBConnection.class) {
				if (dbconnection == null)
					dbconnection = new DBConnection();
			}
		}
		return dbconnection;
	}

	public Connection getConLMS() {
		try {
			if (lmsConnection == null) {
				String url = ConfigManager.getProperty("DBConURL") + "/" + ConfigManager.getProperty("DBLMS")
						+ "?useSSL=FALSE";
				String user = ConfigManager.getProperty("DBConnUser");
				String password = ConfigManager.getProperty("DBConnPwd");
				Class.forName("com.mysql.cj.jdbc.Driver");
				lmsConnection = DriverManager.getConnection(url, user, password);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lmsConnection;
	}

	private PreparedStatement prepareStatement(Connection connection, String query, Object... args)
			throws SQLException {
		PreparedStatement ps = connection.prepareStatement(query);
		int i = 1;
		for (Object arg : args) {
			switch (arg.getClass().getSimpleName().trim()) {
			case "String":
				ps.setString(i++, arg.toString());
				break;
			case "Integer":
				ps.setInt(i++, (Integer) arg);
				break;
			case "Float":
				ps.setFloat(i++, (Float) arg);
				break;
			case "Double":
				ps.setDouble(i++, (Double) arg);
				break;
			default:
				logger.error("incorrect argument type passed for query");
			}
		}
		logger.info(ps.toString());
		return ps;
	}

	public ResultSet executeQuery(Connection connection, String query, Object... args) {
		try {
			PreparedStatement statement = prepareStatement(connection, query, args);
			return statement.executeQuery();
		} catch (Exception dbException) {
			logger.error("exception in execution of query:" + dbException.getMessage());
		}
		return null;
	}

	public boolean executeUpdate(Connection connection, String query, Object... args) {
		try {
			PreparedStatement statement = prepareStatement(connection, query, args);
			return (statement.executeUpdate() == 1);
		} catch (Exception e) {
			logger.error("Exception in updation operation:" + e.getMessage());
		}
		return false;
	}
}