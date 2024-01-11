import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import fr.epita.quiz.web.client.UserHttpClient;
import fr.epita.quiz.web.server.MicroServer;
import fr.epita.quiz.web.server.RootController;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class ApplicationConfiguration {
    @Bean("httpClient")
    public UserHttpClient getHttpClient(){
        return new UserHttpClient("http://localhost:80/test");
    }
    @Bean("rootHandler")
    public HttpHandler rootHandler(){
        return new RootController();
    }
    @Bean
    public MicroServer  getServer(@Qualifier("rootHandler") HttpHandler handler) throws IOException {
        int processors = Runtime.getRuntime().availableProcessors() / 2;
        MicroServer server = new MicroServer(80, processors, "/test", handler);
        return server;
    }
}
