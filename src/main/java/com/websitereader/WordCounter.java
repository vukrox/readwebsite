package src.main.java.com.websitereader;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/** Читает файл и считает слова выводя в консоль результат.
 *
 */
public class WordCounter {

    //Поддерживает 2 варианта разделения (согласно ТЗ и согласно здравой логики)
    private final String delimeterPattern;

    public WordCounter(String delimeterPattern) {
        this.delimeterPattern = delimeterPattern;
    }


    /** Основной рабочий метод:
     * -ридером вычитывает файл, сохраняет в Лист строки,
     * -переводит отдельные слова в массив используя делители,
     * -считает количество слов в HashMap(ключ - слово, значение - количество его повторений).
     * @param fileName - на вход принимает имя файла, которое было указано в аргументах.
     */
    public void countEachWord(String fileName) throws IOException {
        //Вычитываем файл в Вычитываем
        BufferedReader reader = new BufferedReader(new FileReader(fileName));

        //Создаем структуру данных Map<String, Integer> кудв будем сохранять информацию
        //String key - слово, Integervalue: количество найденных собпадений
        Map<String, Integer> frequencey = new HashMap<>();

        //Преобразуем данные из файла (строки файла) в коллекцию строк типа List
        List<String> strings = reader.lines().collect(Collectors.toList());

        //Каждую строку приводим к нижнему регистру
        strings.forEach(String::toLowerCase);

        //Каждую строку разбираем на слова и сохраняем в структуру Map<String, Integer> frequencey
        strings.forEach(x -> runMainTrimProcess(x, frequencey));

        //Выводим на экран итоговую информацию -
        for (Map.Entry<String, Integer> entry : frequencey.entrySet()) {
            System.out.println(entry.getKey().toUpperCase() + " : " + entry.getValue());
        }
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
}