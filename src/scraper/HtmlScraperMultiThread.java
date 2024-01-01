package scraper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class HtmlScraperMultiThread implements Runnable {
    private static final String DOWNLOAD_FOLDER = "data/";
    private final String siteUrl;

    public HtmlScraperMultiThread(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    @Override
    public void run() {
        saveHtml();
    }

    private void saveHtml(){
        try {
            URL url = new URL(siteUrl);
            Scanner scanner = new Scanner(url.openStream());
            String domain = url.getHost().replaceFirst("www\\.", "");

            File downloadFolder = new File(DOWNLOAD_FOLDER);
            if (!downloadFolder.exists()) {
                downloadFolder.mkdir();
            }

            String fileName = DOWNLOAD_FOLDER + domain + "_downloaded_page.html";
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }

            scanner.close();
            bufferedWriter.close();
            fileWriter.close();

        } catch (IOException e) {
            //e.printStackTrace();
        }
    }
}
