package fr.epita.quiz.app;

import fr.epita.quiz.services.QuestionJPADAO;
import fr.epita.quiz.services.api.IQuestionDAO;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class Config {
    @Bean
    LocalSessionFactoryBean getSessionFactory(@Autowired DataSource ds){
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setPackagesToScan("fr.epita.quiz.datamodel");
        sessionFactoryBean.setDataSource(ds);
        Properties extraproperties = new Properties();
        extraproperties.put("hibernate.hbm2ddl.auto", "update");
        extraproperties.put("hibernate.show_sql", "true");

        sessionFactoryBean.setHibernateProperties(extraproperties);
        return sessionFactoryBean;
    }
    @Bean
    DataSource dataSource(){
        return  new DriverManagerDataSource("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
    }
    @Bean("questionDAO")
    public IQuestionDAO questionDAO(SessionFactory factory){
        return new QuestionJPADAO(factory);
    }
}
