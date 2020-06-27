package com.websitereader;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class WebSiteReader {

    public static void writeToFileFromTheWebSite() throws IOException {

        URL url = new URL("https://www.simbirsoft.com/");
        BufferedReader reader = new BufferedReader(new InputStreamReader
                (url.openConnection().getInputStream(), "UTF-8"));

        while (true) {
            String line = reader.readLine();
            if (line == null)
                break;


            String FILENAME = "C:\\Users\\slava\\Documents\\filename.html";
            BufferedWriter bw = null;
            try {
                bw = new BufferedWriter(new FileWriter(FILENAME));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            bw.write(line);

            reader.close();
            bw.close();
        }
    }
}


