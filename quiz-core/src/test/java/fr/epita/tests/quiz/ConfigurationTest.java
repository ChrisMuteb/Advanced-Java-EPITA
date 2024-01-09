package fr.epita.tests.quiz;

import fr.epita.quiz.services.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import java.io.IOException;

public class ConfigurationTest {
    private Configuration configuration;
    @BeforeAll
    public static void globalSetup(){
        System.setProperty("conf.location", "src/test/resources/conf.properties");
    }
    @BeforeEach
    public void setup(){
        this.configuration = Configuration.getInstance();
    }
    @Test
    public void test() throws IOException {
        // Given the initial config
        // When
        String dbUrl = this.configuration.getDBUrl();
        // Then
        assertThat(dbUrl).isNotNull();
        System.out.println(dbUrl);
    }
    @Test
    public void test2(){
        // conditions to create a valid unit test case
        // 1. Given When Then
        // 2. repeatable
        // 3. independent
    }
}
