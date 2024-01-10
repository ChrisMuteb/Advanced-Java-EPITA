import com.sun.net.httpserver.HttpServer;
import fr.epita.quiz.datamodel.User;
import fr.epita.quiz.web.client.UserHttpClient;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;
import java.io.IOException;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class ClientServerCommunicationTest {
    @Inject
    private HttpServer server;
    @Inject
    private UserHttpClient client;
    @Test
    public void test() throws IOException {
        // given
        server.start();
        client.createUser(new User("test", "paris","01234"));

        // when
        List<User> users = client.getUsers();

        // then
        Assertions.assertThat(users).hasSize(1);

        server.stop(2);
    }
}
