package scraper;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;




public class ImageScraperMultiThread implements Runnable {
    private static final String DOWNLOAD_FOLDER = "data/";
    private final Document document;

    public ImageScraperMultiThread(Document document){
        this.document = document;
    }
    @Override
    public void run(){
        scrapeImages();
    }

    private void scrapeImages() {
        Elements images = document.select("img[src]");
        for (Element image : images) {
            String imageUrl = image.absUrl("src");

            saveImage(imageUrl);
        }
    }

    private void saveImage(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            InputStream is = url.openStream();
            String[] tokens = imageUrl.split("/");
            String fileName = tokens[tokens.length - 1];

            Path folderPath = FileSystems.getDefault().getPath(DOWNLOAD_FOLDER);
            if (!Files.exists(folderPath)) {
                Files.createDirectory(folderPath);
            }

            Path filePath = folderPath.resolve(fileName);

            try (FileOutputStream fos = new FileOutputStream(filePath.toFile());
                 ReadableByteChannel rbc = Channels.newChannel(is)) {
                fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            }

        } catch (IOException e) {
            //System.err.println("Error saving image: " + imageUrl);
            //e.printStackTrace();
        }
    }
}
