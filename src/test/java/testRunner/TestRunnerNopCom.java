package testRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions
	(
			features = {".//features//Customers.feature",".//features//Login.feature"},	//  .// represents project folder,
			glue = "stepDefinitions.NopCom",
			tags = ("@sanity"),
			name = {"Successful login with valid credentials","Search Customer by Email"},
			dryRun = false,			// true means no actual run only to crosscheck if every feature step has corresponding step definition/method implemented
			monochrome =  true,
			plugin = {"pretty",		//output in console very clearly
					"html:target/report.html"	//"html:test-output/report.html" -->can't generate test-output folder automatically -Couldn't create parent directories 
			}
	)	
public class TestRunnerNopCom {}
