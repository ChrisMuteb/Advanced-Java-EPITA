package fr.epita.tests.quiz;

import fr.epita.quiz.services.Configuration;
import fr.epita.quiz.services.QuestionDAOWithDI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;
import javax.inject.Named;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = BeanConfiguration.class)
public class TestWithDi {
    @Inject
    String testFromDI;
    @Inject
    @Named("test")
    Configuration configuration;
//    @Inject
//    QuestionDAOWithDI

    @Test
    public void firstTestFromDI(){
        Assertions.assertNotNull(testFromDI);
    }
}
