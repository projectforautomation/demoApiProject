package com.skilrock.dge.boMgmt.service;

import static io.restassured.RestAssured.given;

import com.skilrock.dge.boMgmt.bean.BoLoginBean;
import com.skilrock.dge.boMgmt.dao.BoLoginDao;
import com.skilrock.dge.common.utils.RMSUrl;

import io.restassured.response.Response;

public class BoLogin {
	
	BoLoginDao boLoginDao = new BoLoginDao();
	
	public boolean performLogin(String username, String password) {
		String queryParam = "{\"userName\":\"" + username + "\",\"password\":\"" + password + "\"}";
	try {
		Response response = given().contentType("application/json")
				.queryParam("json", queryParam).get(RMSUrl.loginURL);
		
				
				String sessionId = response.jsonPath().getString("data.sessionId");
				
				BoLoginBean.setSessionId(sessionId);
	} catch(Exception e) {
		e.printStackTrace();
		return false;
	}
		return true;
	}
	
	public boolean verifySessionId(String userName) {
		
		String sessionFromDB = boLoginDao.getSessionId(userName);
		
		if(sessionFromDB != null) {
			if(BoLoginBean.getSessionId().equals(sessionFromDB))
				return true;
		}
		return false;
	}

}
