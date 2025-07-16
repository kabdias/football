package com.matawan.football.configuration;

import com.matawan.football.FootballApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@CucumberContextConfiguration
@ContextConfiguration(classes = FootballApplication.class)
public class ContextTestingConfiguration {
}
