package com.sparta.academy.mfix_mongodb_api.framework.cucumber;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
        plugin = {"pretty", "html:target/report.html", "json:target/report.json"},
        publish = true
)
public class TestRunner {

}
