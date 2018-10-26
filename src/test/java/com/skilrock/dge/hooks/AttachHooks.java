package com.skilrock.dge.hooks;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.skilrock.dge.common.utils.ConfigManager;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class AttachHooks {
	private static Logger LOGGER = LoggerFactory.getLogger(AttachHooks.class);

	private Scenario scenario;

	@Before
	public void setUp(Scenario scenario) throws IOException {
		LOGGER.info("INSIDE BEFORE HOOKS");
		ConfigManager.loadConfig();
		this.scenario = scenario;
		// LOGGER.info("SCENARIO IN EXECUTION : " + scenario.getName());
	}

	@After
	public void tearDown() throws InstantiationException, IllegalAccessException, IOException {
		{

			/*
			 * if(DBConnection.getInstance().closeConnection()){
			 * LOGGER.info("All DB Connection Closed Sucessfully"); }
			 */
			LOGGER.info("API EXECUTION STOP FOR SCENARIO : " + scenario.getName());
		}
	}

}