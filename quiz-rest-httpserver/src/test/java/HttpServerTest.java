import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;
import fr.epita.quiz.datamodel.User;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class HttpServerTest {
    public static void main(String[] args) throws IOException {
        // server
        HttpServer server = HttpServer.create(new InetSocketAddress(80), 0);
        HttpContext context = server.createContext("/test");
        context.setHandler(ex -> {
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
        });
        server.start();

        connectAndGet();
        connectAndPost();

        server.stop(200);
    }

    private static void connectAndGet() throws IOException {
        // client
        HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:80/test").openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        int responseCode = connection.getResponseCode();
        System.out.println("response code - " + responseCode);
        InputStream inputStream = connection.getInputStream();
        byte[] bytes = inputStream.readAllBytes();
        String responseText = new String(bytes);
        System.out.println(responseText);

        ObjectMapper mapper = new ObjectMapper();
        List<User> users = mapper.readValue(responseText, new TypeReference<List<User>>() {});
        System.out.println("The users list size is:" + users.size());
        System.out.println("first user: " + users.get(0));
        connection.disconnect();
    }

    private static void connectAndPost() throws IOException {
        // client
        HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:80/test").openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true); // activate the fact that we have an output
        User user = new User("test from POST", "Paris", "123");
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(user);
        byte[] bytes = jsonString.getBytes();
        connection.getOutputStream().write(bytes);//outputstream to write
        connection.connect();

        int responseCode = connection.getResponseCode();//read a response code
        System.out.println("response code - " + responseCode);
        System.out.println(new String(connection.getInputStream().readAllBytes()));
        connection.disconnect();
    }
}
