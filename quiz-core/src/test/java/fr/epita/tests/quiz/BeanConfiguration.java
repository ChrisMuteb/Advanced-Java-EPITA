package fr.epita.tests.quiz;

import fr.epita.quiz.services.QuestionDAOWithDI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class BeanConfiguration {
    @Bean("defaultString")
    public String getTestStringValue(){
        return "testFromDI";
    }

    @Bean("testStringInjection")
    public String getTestStringValue2(){
        return "testFromDI";
    }
    @Bean("test")
    public fr.epita.quiz.services.Configuration configuration(){
        return fr.epita.quiz.services.Configuration.getInstance();
    }
    @Bean
    QuestionDAOWithDI questionDAOWithDI(){
        return new QuestionDAOWithDI();
    }

    @Bean
    DataSource dataSource(@Autowired fr.epita.quiz.services.Configuration configuration){
        return  new DriverManagerDataSource(configuration.getDBUrl());
    }
}
