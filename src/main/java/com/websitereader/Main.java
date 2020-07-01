package com.websitereader;


import com.websitereader.service.WebsiteReaderService;
import com.websitereader.service.WebsiteReaderServiceImpl;
import com.websitereader.util.PropertyHelper;

import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {
        PropertyHelper propertyHelper = new PropertyHelper();
        Properties properties = propertyHelper.loadProperties();
        WebsiteReaderService websiteReaderService = new WebsiteReaderServiceImpl(properties);
        websiteReaderService.read();
        List<String> strings = websiteReaderService.write();
        Map<String, Integer> frequency = websiteReaderService.countEachWord(strings);
        websiteReaderService.save(frequency);
    }
}
