package com.websitereader;

import java.io.IOException;
import java.util.Properties;


public class Main {

    public static void main(String[] args) throws IOException {

        PropertyHelper helper = new PropertyHelper();

        Properties prop = helper.loadProperties();

        String url = prop.getProperty("url");

        String fileName = prop.getProperty("fileName");

        Writer writer = new Writer();

        writer.writeToFileFromTheWebSite(url, fileName);

        Reader reader = new Reader();

        reader.readFromFile(fileName);

        WordCounter counter = new WordCounter();

        counter.countEachWord(fileName);

    }


}
