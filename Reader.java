package com.websitereader;

import java.io.*;
import java.net.URL;

public class Reader {

    public String readFromFile(String fileName) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        String         ls = System.getProperty("line.separator");

        try{
            while ((line = reader.readLine()) !=null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }
            return stringBuilder.toString();
        }finally {
            reader.close();
        }
    }
}
