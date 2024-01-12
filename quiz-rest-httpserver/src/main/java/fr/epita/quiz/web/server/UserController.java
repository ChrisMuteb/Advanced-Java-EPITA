package fr.epita.quiz.web.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import fr.epita.quiz.datamodel.User;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class UserController implements HttpHandler {
    @Override
    public void handle(HttpExchange ex) throws IOException {
            String requestMethod = ex.getRequestMethod();
            ObjectMapper mapper = new ObjectMapper();
            switch (requestMethod){
                case "GET":
                    System.out.println("test from get");
                    User user = new User("test", "Paris", "123");
                    List<User> users = Arrays.asList(user);
                    String jsonString = mapper.writeValueAsString(users);
                    byte[] bytes = jsonString.getBytes();
                    ex.sendResponseHeaders(200, bytes.length);
                    ex.getResponseBody().write(bytes);
                    break;
                case "POST":
                    System.out.println("test from post");
                    User userToBeCreated = mapper.readValue(ex.getRequestBody(), User.class);//find an instance of user in the request body
                    System.out.println(userToBeCreated);
                    // should call a dao.create() operation
                    byte[] response = "user created with id 1".getBytes();
                    ex.sendResponseHeaders(201, response.length);
                    ex.getResponseBody().write(response);
                    break;
            }
    }
}
