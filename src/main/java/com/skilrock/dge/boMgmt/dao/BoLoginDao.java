package com.skilrock.dge.boMgmt.dao;

import java.sql.ResultSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.skilrock.dge.boMgmt.query.BoLoginQuery;
import com.skilrock.dge.common.DBConnection;

public class BoLoginDao {
	

	private static Logger logger = LoggerFactory.getLogger(BoLoginDao.class);
	
	public String getSessionId(String userName) {
		String sessionId = null;
		try {
			ResultSet rs = DBConnection.getInstance().executeQuery(DBConnection.getInstance().getConLMS()
					, BoLoginQuery.sessionQuery, userName);
			
			if(rs.next()) {
				sessionId = rs.getString(1);
				logger.info("Session id obtained from database is: " + sessionId);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return sessionId;
	}

}
