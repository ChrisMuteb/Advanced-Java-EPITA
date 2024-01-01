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
            switch (requestMethod){
                case "GET":
                    System.out.println("test from get");
                    User user = new User("test", "Paris", "123");
                    List<User> users = Arrays.asList(user);
                    ObjectMapper mapper = new ObjectMapper();
                    String jsonString = mapper.writeValueAsString(users);
                    byte[] bytes = jsonString.getBytes();
                    ex.sendResponseHeaders(200, bytes.length);
                    ex.getResponseBody().write(bytes);
                    break;
                case "POST":
                    System.out.println("test from post");
                    break;
            }
        });
        server.start();

        connectAndGet();
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

    }
}
