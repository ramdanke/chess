package log;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LoggerUtility {

    public static Logger getLogger(Class<?> logClass, String type) {

        String configFile = null;

        if (type.equals("text")) {
            configFile = "log/log4j-text.properties";
        } 
        else if (type.equals("html")) {
            configFile = "log/log4j-html.properties";
        } 
        else {
            throw new IllegalArgumentException("Type inconnu");
        }

        
        PropertyConfigurator.configure(
            LoggerUtility.class.getClassLoader().getResource(configFile)
        );

        return Logger.getLogger(logClass);
    }
}