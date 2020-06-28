package com.websitereader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyHelper {

    public Properties loadProperties(){
        final Properties properties = new Properties();
        try (final InputStream stream =
                     this.getClass().getResourceAsStream("application.properties")) {
            properties.load(stream);
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
        }
        return properties;
    }


}
