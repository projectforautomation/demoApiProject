package com.skilrock.dge.boMgmt.stepdefinitions;

import org.junit.Assert;

import com.skilrock.dge.boMgmt.service.BoLogin;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class BoLoginStepDef {
	
	BoLogin boLogin = new BoLogin();
	
	@When("^login is performed through api with (.*) and (.*)$")
	public void perform_bo_login_through_api(String userName,String passwd) {
		Assert.assertTrue(boLogin.performLogin(userName, passwd));
	}
	
	@Then("^verify sessionId of (.*) from database$")
	public void verify_session_from_database(String userName) {
		Assert.assertTrue(boLogin.verifySessionId(userName));
	}

}
