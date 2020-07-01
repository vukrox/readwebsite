package com.websitereader.service;

import com.websitereader.util.DBHelper;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;


public class WebsiteReaderServiceImpl implements WebsiteReaderService {

    private final String urlName;
    private final String fileName;
    private final DBHelper dbHelper;
    private final String delimeterPattern;

    public WebsiteReaderServiceImpl(Properties properties) {
        this.urlName = properties.getProperty("url");
        this.fileName = properties.getProperty("fileName");
        this.dbHelper = new DBHelper(properties);
        this.delimeterPattern = properties.getProperty("delemiterPattern");
    }

    @Override
    public void read() {
        try {
            URL url = new URL(urlName);
            try (InputStream inputStream = url.openConnection().getInputStream()) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                //То что прочитали, добавляем в ArrayList для дальнейшей работы.
                ArrayList<String> arrayList = new ArrayList<>();
                while (reader.readLine() != null) {
                    arrayList.add(reader.readLine());
                }
                //То что записали в АЛ, переписываем BW в файл.
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                    for (String s : arrayList) {
                        if (s != null) {
                            String stringForWrite = String.format("%s%s", s, "\n");
                            writer.write(stringForWrite);
                        }
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> write() {
        List<String> strings = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            strings = reader.lines().map(String::toLowerCase).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strings;
    }

    @Override
    public Map<String, Integer> countEachWord(List<String> strings) {
        Map<String, Integer> frequencey = new HashMap<>();
        strings.forEach(x -> runMainTrimProcess(x, frequencey));

        //Выводим на экран итоговую информацию -
        for (Map.Entry<String, Integer> entry : frequencey.entrySet()) {
            System.out.println(entry.getKey().toUpperCase() + " : " + entry.getValue());
        }
        return frequencey;
    }

    private void runMainTrimProcess(String x, Map<String, Integer> frequencey) {

        //Разбиваем строку на слова, разделителем является символ из regex
        String[] wordsFromString = x.split(delimeterPattern);

        //Каждое найденное слово обрабатываем
        for (String processed : wordsFromString) {
            //если слово не пустое и не null
            if (processed != null && !processed.isEmpty()) {

                if (!frequencey.containsKey(processed)) {
                    //если в итоговой структуре данных отсутсвует найденное слово, добавляем его как key, а value = 1
                    frequencey.put(processed, 1);
                } else {
                    // если в структуре данных имеется ключ, то к value добавляем 1
                    frequencey.put(processed, frequencey.get(processed) + 1);
                }
            }
        }
    }

    @Override
    public void save(Map<String, Integer> frequency) {
        createTable();
        frequency.forEach((word, count) -> {
            try {
                dbHelper.executeUpdate("INSERT INTO frequency (word, count) VALUES(" + word + ",'" + count + "');");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

    }

    private void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS `frequency` (" +
                "`word` VARCHAR(50) PRIMARY KEY," +
                "`count` INT(6) NOT NULL" +
                ");";
        try {
            dbHelper.execute(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
