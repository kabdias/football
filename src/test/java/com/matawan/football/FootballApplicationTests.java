package com.matawan.football;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/java/resources",
		glue = {"com/matawan/football/configuration", "com/matawan/football/accaptance/steps"},
		plugin = {"pretty", "html:FeaturesReport/report.html"}
)
class FootballApplicationTests {

}
