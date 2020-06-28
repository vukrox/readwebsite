package com.websitereader;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class WordCounter {

    public void countEachWord(String fileName) throws IOException {
        //Вычитываем файл в Вычитываем
        BufferedReader reader = new BufferedReader(new FileReader(fileName));

        //Создаем структуру данных Map<String, Integer> кудв будем сохранять информацию
        //String key - слово, Integervalue: количество найденных собпадений
        Map<String, Integer> frequencey = new HashMap<>();

        //Преобразуем данные из файла (строки файла) в коллекцию строк типа List
        List<String> strings = reader.lines().collect(Collectors.toList());

        //Каждую строку приводим к нижнему регистру
        strings.forEach(x -> x.toLowerCase());

        //Каждую строку разбираем на слова и сохраняем в структуру Map<String, Integer> frequencey
        strings.forEach(x -> runMainTrimProcess(x, frequencey));

        //Выводим на экран итоговую информацию -
        for (Map.Entry<String, Integer> entry : frequencey.entrySet()) {
            System.out.println(entry.getKey().toUpperCase() + " : " + entry.getValue());
        }
    }

    private void runMainTrimProcess(String x, Map<String, Integer> frequencey) {
        String regex = "[' ', ',', '.', '!', '?','\"', ';', ':', '\\[', '\\]', '\\(', '\\)', '\\n', '\\r', '\\t']";

        //Разбиваем строку на слова, разделителем является символ из regex
        String[] wordsFromString = x.split(regex);

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
}