package runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/java/features",
		glue = "step_defs",
		plugin = { "pretty" , "html:target/html-reports" }
		)
public class BotTestRunner {

}
