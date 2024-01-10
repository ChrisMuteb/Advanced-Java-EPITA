import com.sun.net.httpserver.HttpServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class HttpServerTestThroughDI {
    @Inject
    HttpServer server;

    @BeforeEach
    public void setup(){
        server.start();
    }

    @Test
    public void firstTest(){
        System.out.println("I do some test");
    }

    @AfterEach
    public void stop(){
        server.stop(1);
    }
}
