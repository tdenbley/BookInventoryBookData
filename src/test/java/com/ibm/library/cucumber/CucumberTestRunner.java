package com.ibm.library.cucumber;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "classpath:features", 
		plugin = { "pretty", "html:target/Destination" }
		)

public class CucumberTestRunner {

}
