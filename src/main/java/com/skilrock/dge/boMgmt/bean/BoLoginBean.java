package com.skilrock.dge.boMgmt.bean;

public class BoLoginBean {
	
	private static String sessionId = null;

	public static String getSessionId() {
		return sessionId;
	}

	public static void setSessionId(String sessionId) {
		BoLoginBean.sessionId = sessionId;
	}
	
	

}
