package src.main.java.com.websitereader;

import src.main.java.com.jdbc.Database;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class Main {

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {

        PropertyHelper helper = new PropertyHelper();

        Properties prop = helper.loadProperties();

        String url = prop.getProperty("url");

        String fileName = prop.getProperty("fileName");

        String delimeterPattern = prop.getProperty("delemiterPattern");

        Writer writer = new Writer();

        writer.writeToFileFromTheWebSite(url, fileName);

        WordCounter counter = new WordCounter(delimeterPattern);

        Map<String, Integer> frequency = counter.countEachWord(fileName);

        Database db = new Database();
        String sql = "CREATE TABLE IF NOT EXISTS `table` ("+
                "`key` VARCHAR(50) PRIMARY KEY,"+
                "`value` INT(6) NOT NULL"+
                ");";

        db.execute(sql);
        for ( Map.Entry<String,Integer> next : frequency.entrySet()) {
            db.executeUpdate("INSERT INTO table (Key, Value) VALUES(" + next.getKey() + ",'" + next.getValue() + "');");
        }
        db.closeConnection();
    }
}
