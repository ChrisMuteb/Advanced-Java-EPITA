import com.sun.net.httpserver.HttpHandler;
import fr.epita.quiz.web.client.UserHttpClient;
import fr.epita.quiz.web.server.MicroServer;
import fr.epita.quiz.web.server.UserController;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class ApplicationConfiguration {
    @Bean("httpClient")
    public UserHttpClient getHttpClient(){
        return new UserHttpClient("http://localhost:80/test");
    }
    @Bean("rootHandler")
    public HttpHandler rootHandler(){
        return new UserController();
    }
    @Bean
    public MicroServer  getServer(@Qualifier("rootHandler") HttpHandler handler) throws IOException {
        int processors = Runtime.getRuntime().availableProcessors() / 2;
        MicroServer server = new MicroServer(80, processors, "/test", handler);
        return server;
    }
}
