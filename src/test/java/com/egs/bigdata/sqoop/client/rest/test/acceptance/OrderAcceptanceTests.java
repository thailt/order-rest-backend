package com.egs.bigdata.sqoop.client.rest.test.acceptance;

import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/acceptance")
@WebAppConfiguration
public class OrderAcceptanceTests {

}
