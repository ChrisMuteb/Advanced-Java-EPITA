package fr.epita.quiz.services;

import fr.epita.quiz.datamodel.Question;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAO {
    public static final String INSERT_STMT = "INSERT INTO QUESTIONS(title) values (?)";
    public static final String SELECT_STMT = "SELECT id, title from QUESTIONS WHERE title like ?)";

    public void create(Question question) throws SQLException, IOException {
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement(INSERT_STMT);
        stmt.setString(1, question.getTitle());
        stmt.execute();
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(Configuration.getInstance().getDBUrl());
    }

    public List<Question> search(Question question) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement(SELECT_STMT);
        stmt.setString(1, question.getTitle() + "%");
        ResultSet resultSet = stmt.executeQuery();
        List<Question> questions = new ArrayList<>();
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String title =  resultSet.getString("title");
            Question currentQuestion = new Question();
            currentQuestion.setTitle(title);
            currentQuestion.setId(id);
            questions.add(currentQuestion);
        }
        return questions;
    }
}
