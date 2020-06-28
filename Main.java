package com.websitereader;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class Main {
    public static void main(String[] args) throws IOException {

        Writer writer = new Writer();
        URL url = new URL("https://www.simbirsoft.com/");
        String fileName = "C:\\Users\\slava\\Documents\\filename.html";
        writer.writeToFileFromTheWebSite(url,fileName);

        Reader reader = new Reader();
        String text = reader.readFromFile(fileName);


        WordCounter counter = new WordCounter();
        counter.countEachWord(fileName);
    }
}
