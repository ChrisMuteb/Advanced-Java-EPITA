package fr.epita.tests.quiz;

import fr.epita.quiz.services.QuestionDAOWithDI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public String getTestStringValue(){
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
}
