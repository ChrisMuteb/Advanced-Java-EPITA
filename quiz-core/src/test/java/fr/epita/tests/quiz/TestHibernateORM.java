package fr.epita.tests.quiz;

import fr.epita.quiz.datamodel.Question;
import jakarta.persistence.Table;
import org.assertj.core.api.Assertions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = BeanConfiguration.class)
@Commit
public class TestHibernateORM {
    @Inject
    SessionFactory sf;
    @Test
    public void testConf(){
        Question question = new Question();
        question.setTitle("what is Hibernate?");
        Question question2 = new Question();
        question2.setTitle("Hibernate? what is this?");
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(question);
        session.persist(question2);
        transaction.commit();
        // to assert that it worked
        // it simply select * from questions
        List<Question> allQuestions = session
                .createQuery("from Question", Question.class).list();
        Assertions.assertThat(allQuestions).hasSize(1);

    }
}
