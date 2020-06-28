package com.websitereader;

import java.io.*;
import java.util.*;

public class WordCounter {
    public void countEachWord(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));

        Map <String, Integer> frequencey = new HashMap<>();

        String line = reader.readLine();

        while (line != null) {
            if (!line.trim().equals("")){
                String [] words = line.split(" ");

                for (String word: words){
                    if (word == null || word.trim().equals("")){
                        continue;
                    }

                    String processed = word.toLowerCase();
                    processed = processed.replace(","," ");
                    processed = processed.replace("."," ");
                    processed = processed.replace("!"," ");
                    processed = processed.replace("?"," ");
                    processed = processed.replace(";"," ");
                    processed = processed.replace(":"," ");
                    processed = processed.replace("?"," ");
                    processed = processed.replace("["," ");
                    processed = processed.replace("]"," ");
                    processed = processed.replace("("," ");
                    processed = processed.replace(")"," ");
                    processed = processed.replace("\""," ");
                    processed = processed.replace("\n"," ");
                    processed = processed.replace("\t"," ");
                    processed = processed.replace("\r"," ");


                    if (frequencey.containsKey(processed)) {
                        frequencey.put(processed, frequencey.get(processed)+1);
                    } else {
                        frequencey.put(processed, 1);
                    }
                }

            }
            line =reader.readLine();
        }
        for (Map.Entry<String, Integer> entry : frequencey.entrySet()) {
            System.out.println(entry.getKey().toUpperCase()+" : "+entry.getValue());
        }
    }
}
