package fr.epita.quiz.services;

import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.services.api.IQuestionDAO;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAOWithDI implements IQuestionDAO {
    @Inject
    DataSource ds;
    public static final String INSERT_STMT = "INSERT INTO QUESTIONS(title) values (?)";
    public static final String SELECT_STMT = "SELECT id, title from QUESTIONS WHERE title like ?)";
    public void create(Question question) {
        Connection connection = null;
        try {
            connection = ds.getConnection();
            PreparedStatement stmt = connection.prepareStatement(INSERT_STMT);
            stmt.setString(1, question.getTitle());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void update(Question question) {

    }
    @Override
    public void delete(Question question) {

    }
    public List<Question> search(Question question) {
        List<Question> questions = new ArrayList<>();
        try{
            Connection connection = ds.getConnection();
            PreparedStatement stmt = connection.prepareStatement(SELECT_STMT);
            stmt.setString(1, question.getTitle() + "%");
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String title =  resultSet.getString("title");
                Question currentQuestion = new Question();
                currentQuestion.setTitle(title);
                currentQuestion.setId(id);
                questions.add(currentQuestion);
            }
        }catch (Exception e){

        }
        return questions;
    }
}


//    private static Connection getConnection() throws SQLException {
//        return DriverManager.getConnection(Configuration.getInstance().getDBUrl());
//    }