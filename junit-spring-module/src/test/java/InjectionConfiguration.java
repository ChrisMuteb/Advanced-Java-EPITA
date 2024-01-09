import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InjectionConfiguration {
    @Bean("testString")
    public String firstString(){
        return "hello!";
    }
}
