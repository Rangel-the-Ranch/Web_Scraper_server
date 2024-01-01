package scraper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class HtmlScraperMultiThread implements Runnable {
    private static String DOWNLOAD_FOLDER = "data/";
    private final String siteUrl;


    public HtmlScraperMultiThread(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    @Override
    public void run() {
        saveHtml();
    }
    public static boolean changeFolder(String folderName){
        if(folderName == null || folderName.trim().isEmpty() ){
            return false;
        }
        for( char c : folderName.toCharArray() ){
            if ( !(Character.isLetterOrDigit(c) || c == '_' || c =='-' || c=='.' || c==',') ){
                return false;
            }
        }
        DOWNLOAD_FOLDER = folderName+'/';
        return true;
    }

    private void saveHtml(){
        try {
            URL url = new URL(siteUrl);
            Scanner scanner = new Scanner(url.openStream());
            String domain = url.getHost().replaceFirst("www\\.", "");

            File downloadFolder = new File(DOWNLOAD_FOLDER);
            if (!downloadFolder.exists()) {
                if(!downloadFolder.mkdir()){
                    System.out.println("Error creating directory");
                    return;
                }
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
