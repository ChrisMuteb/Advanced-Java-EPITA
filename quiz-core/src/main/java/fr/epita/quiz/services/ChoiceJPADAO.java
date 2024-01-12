package fr.epita.quiz.services;

import fr.epita.quiz.datamodel.Choice;
import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.services.api.IChoiceDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ChoiceJPADAO implements IChoiceDAO {
    SessionFactory sessionFactory;
    public ChoiceJPADAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public void create(Choice choice){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(choice);
        transaction.commit();
    }
    public void update(Choice choice){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(choice);
        transaction.commit();
    }
    public void delete(Choice choice){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(choice);
        transaction.commit();
    }
    public List<Choice> search(Choice criteria){
        Query query = sessionFactory.openSession().createQuery("from Choice c where c.question.id = :qid", Choice.class);
        query.setParameter("qid", criteria.getQuestion().getId());
        List list = query.list();
        return list;
    }
    @Override
    public Choice getById(Object id) {
        return null;
    }
}
