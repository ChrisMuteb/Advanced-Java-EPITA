package fr.epita.tests.quiz;

import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.services.Configuration;
import fr.epita.quiz.services.QuestionDAOWithDI;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = BeanConfiguration.class)
public class TestWithDi {
//    @Inject
//    @Named("testStringInjection")
//    String testFromDI;
    @Inject
    @Named("test")
    Configuration configuration;
    @Inject
    QuestionDAOWithDI daoWithDI;
    @Inject
    DataSource ds;
    @BeforeAll
    public static void setup(){
        System.setProperty("conf.location", "src/test/resources/conf.properties");
    }
    @BeforeEach
    public void dbSetup() throws SQLException {
        Connection connection = this.ds.getConnection();
        PreparedStatement preparedStatement = connection
                .prepareStatement("CREATE TABLE QUESTIONS (id integer auto_increment, title varchar(255) not null)");
        preparedStatement.execute();
    }
//    @Test
//    public void firstTestFromDI(){
//        Assertions.assertNotNull(testFromDI);
//    }

    @Test
    @DisplayName("When a question is inserted then it should be found in database")
    public void testDAOCreation() throws SQLException, IOException {
        // Given
        Question question = new Question();
        question.setTitle("test question");

        // When
        this.daoWithDI.create(question);

        // then
        PreparedStatement preparedStatement = ds.getConnection().prepareStatement("SELECT count(*) as cnt from questions");
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        int cnt = resultSet.getInt("cnt");

        assertThat(cnt).isEqualTo(1);

        // cleanup

    }
}
