package src.main.java.com.websitereader;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**Читает с сайта и записывает в файл.
 *
 */
public class Writer {

    /**Метод с говорящим названием; на вход передаем URL сайта для парсинга и файл в который будем сохранять
     *Стадия 1. - читаем информацию с сайта и записываем в файл на компьютер.
     * @param urlName
     * @param fileName
     * @throws IOException
     */

    public void writeToFileFromTheWebSite(String urlName, String fileName) throws IOException {

        URL url = new URL(urlName);

        //Открываем поток, читаем.
        try (InputStream inputStream = url.openConnection().getInputStream()) {
            BufferedReader reader = new BufferedReader(new InputStreamReader
                    (inputStream, StandardCharsets.UTF_8));

            //То что прочитали, добавляем в ArrayList для дальнейшей работы.
            ArrayList<String> arrayList = new ArrayList<>();
            while (reader.readLine() != null) {
                arrayList.add(reader.readLine());
            }
            reader.close();

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


