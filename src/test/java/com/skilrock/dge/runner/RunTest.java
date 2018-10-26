package com.skilrock.dge.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
@RunWith(Cucumber.class)
@CucumberOptions(plugin={"pretty","json:target/cucumberreports.json"},
glue = "com.skilrock.dge", 
features = "src/test/resources/features",
tags={"@login"},
dryRun = false,
monochrome=false)
public class RunTest {
};
