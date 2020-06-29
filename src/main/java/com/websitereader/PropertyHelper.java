package src.main.java.com.websitereader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**Интерфейс взаимодействия с программой вынесен в Properties.
 * Указывайте адрес сайта и конечный файл в "application.properties"
 *
 */
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
