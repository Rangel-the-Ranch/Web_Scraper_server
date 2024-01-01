package scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.util.Set;
import java.util.HashSet;



public class ImageScraper {
    private static final int MAX_DEPTH = 5;
    private static final String DEFAULT_WEBSITE = "https://example.com";
    private final Set<String> visitedUrls = new HashSet<>();

    public static void main(String[] args) {
        ImageScraper scraper = new ImageScraper();
        switch (args.length) {
            case 0 -> scraper.crawl(DEFAULT_WEBSITE, MAX_DEPTH);
            case 1 -> scraper.crawl(args[0], MAX_DEPTH);
            default -> {
                int depth = MAX_DEPTH - Integer.parseInt(args[1]);
                if (depth < 0) {
                    depth = 0;
                }
                scraper.crawl(args[0], depth);
            }
        }
    }

    private void crawl(String url, int depth) {
        if (depth > MAX_DEPTH || visitedUrls.contains(url)) {
            return;
        }
        try {
            visitedUrls.add(url);

            Document document = Jsoup.connect(url).get();
            ImageScraperMultiThread downloader = new ImageScraperMultiThread(document);
            Thread myThread = new Thread(downloader);
            myThread.start();
            //myThread.run();

            Elements links = document.select("a[href]");
            for (Element link : links) {
                String nextUrl = link.absUrl("href");
                crawl(nextUrl, depth + 1);
            }
        } catch (IOException e) {
            //e.printStackTrace();
        }
    }
}
