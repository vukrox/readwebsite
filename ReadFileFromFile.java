package com.websitereader;

import java.io.*;
import java.net.URL;

public class ReadFileFromFile {

    public static void readFromFile() throws IOException {

        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\slava\\Documents\\filename.html");
        BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));

        while (true) {
            String line = reader.readLine();
            if (line == null)
                break;
            System.out.println(line);
        }
    }
}
