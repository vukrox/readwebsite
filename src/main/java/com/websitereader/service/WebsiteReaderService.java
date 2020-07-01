package com.websitereader.service;

import java.util.List;
import java.util.Map;

public interface WebsiteReaderService {

    void read();

    List<String> write();

    Map<String, Integer> countEachWord(List<String> strings);

    void save(Map<String, Integer> frequency);

}
