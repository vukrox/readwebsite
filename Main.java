package com.websitereader;

import java.io.IOException;

import static com.websitereader.ReadFileFromFile.readFromFile;
import static com.websitereader.WebSiteReader.writeToFileFromTheWebSite;

public class Main {
    public static void main(String[] args) throws IOException {
        writeToFileFromTheWebSite();
        readFromFile();

    }
}
