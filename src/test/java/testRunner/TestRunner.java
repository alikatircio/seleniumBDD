package testRunner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(features = {"Features/Login.feature", "Features/Customers.feature"}, glue = "stepDefinition", dryRun = false, plugin = {"pretty", "html:test-output"})
public class TestRunner {  

}
