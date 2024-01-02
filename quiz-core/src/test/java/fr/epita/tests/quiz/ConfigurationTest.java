package fr.epita.tests.quiz;

import fr.epita.quiz.services.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ConfigurationTest {
    @Test
    public void test() throws IOException {
        System.setProperty("conf.location", "src/test/resources/conf.properties");
        Configuration configuration = Configuration.getInstance();
        String dbUrl = configuration.getDBUrl();
        if(dbUrl != null){
            System.out.println(dbUrl);
        }else{
            Assertions.fail();
        }
    }
}
