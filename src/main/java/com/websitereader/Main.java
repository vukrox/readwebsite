package src.main.java.com.websitereader;

import java.io.IOException;
import java.util.Properties;


public class Main {

    public static void main(String[] args) throws IOException {

        PropertyHelper helper = new PropertyHelper();

        Properties prop = helper.loadProperties();

        String url = prop.getProperty("url");

        String fileName = prop.getProperty("fileName");

        String delimeterPattern = prop.getProperty("delemiterPattern");

        Writer writer = new Writer();

        writer.writeToFileFromTheWebSite(url, fileName);

        WordCounter counter = new WordCounter(delimeterPattern);

        counter.countEachWord(fileName);

    }


}
