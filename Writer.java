package com.websitereader;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Writer {

    //Стадия 1. - читаем информацию с сайта и записываем в файл на компьютер.
    //Метод с говорящим названием; на вход передаем URL сайта для парсинга и файл в который будем сохранять
    public void writeToFileFromTheWebSite(URL url, String fileName) throws IOException {

        //Открываем поток, читаем.
        try (InputStream inputStream = url.openConnection().getInputStream()) {
            BufferedReader reader = new BufferedReader(new InputStreamReader
                    (inputStream, "UTF-8"));

            //То что прочитали, добавляем в ArrayList для дальнейшей работы.
            ArrayList<String> arrayList = new ArrayList<>();
            while (reader.readLine() != null) {
                arrayList.add(reader.readLine());
            }
            reader.close();

//            System.out.println("See what's been parsed");
//            for (int i = 0; i < arrayList.size(); i++) {
//                System.out.println(arrayList.get(i));
//            }

            //То что записали в АЛ, переписываем BW в файл.
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
                for (int i = 0; i < arrayList.size(); i++) {
                    if (arrayList.get(i) != null) {
                        String stringForWrite = String.format("%s%s", arrayList.get(i), "\n");
                        bw.write(stringForWrite);
                    }
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}


