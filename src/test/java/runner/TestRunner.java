package runner;


import org.testng.annotations.DataProvider;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
		plugin = {"pretty", "html:target/userAPI.html","json:target/userAPI.json"
				//"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm" , 
				//"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				//"com.aventstack.chaintest.plugins.ChainTestCucumberListener:"
				}, //reporting purpose
		monochrome=false,  //console output color
		//tags = "@tag", //tags from feature file
		features = {"src/test/resources/features/"
					}, //location of feature files
		glue= {"stepDefinition","Hooks"}) //location of step definition files


public class TestRunner extends AbstractTestNGCucumberTests {
	
	@Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
				
		return super.scenarios();
    }

}