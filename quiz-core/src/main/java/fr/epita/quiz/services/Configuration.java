package fr.epita.quiz.services;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Configuration {
    private static Configuration instance;
    Properties props;
    public static Configuration getInstance(){
        if(instance == null){
            try {
                instance = new Configuration();
            }catch (IOException e){
                throw new RuntimeException(e);
            }
        }
        return instance;
    }
    private Configuration() throws IOException {
        // setup a way to get the conf file location
        props = new Properties();
        String confLocation = System.getProperty("conf.location");
        props.load(new FileInputStream(confLocation));
    }
    public String getValue(String confKey, String defaultValue){
        return this.props.getProperty(confKey, defaultValue);
    }
    public String getDBUrl(){
        return this.props.getProperty("db.url");
    }
}
