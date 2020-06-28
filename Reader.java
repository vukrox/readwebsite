package com.websitereader;

import java.io.*;
import java.net.URL;

public class Reader {

    public String readFromFile(String fileName) throws IOException {

        //Стадия 2.

        //Читаем файл после парсинга с сайта.
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        String         ls = System.getProperty("line.separator");

        try{
            while ((line = reader.readLine()) !=null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }
            line = stringBuilder.toString();
            return line;
        }finally {
            reader.close();
        }
    }
}
