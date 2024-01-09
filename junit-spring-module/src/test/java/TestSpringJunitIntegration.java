import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.inject.Inject;
import javax.inject.Named;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = InjectionConfiguration.class)
public class TestSpringJunitIntegration {
    @Inject
    @Named("testString")
    private String name;
    @Test
    public void firstTest(){
        System.out.println(this.name);
    }
}
