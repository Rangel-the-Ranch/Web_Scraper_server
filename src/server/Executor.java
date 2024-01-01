package server;

import scraper.HtmlScraper;
import scraper.ImageScraper;

public class Executor {
    static public String exec(Command newCommand) {
        switch (newCommand.getCommand()) {
            case "help" -> {
                return "scrapeHTML , scrapeIMG";
            }
            case "scrapeHTML" -> {
                String[] args = new String[newCommand.getArguments().length];
                if(newCommand.getArguments().length >= 1){
                    args[0] = newCommand.getArguments()[0];
                }
                if(newCommand.getArguments().length >= 2){
                    args[1] = newCommand.getArguments()[1];
                }
                HtmlScraper.main(args);
                return "Scraping HTML successful";
            }
            case "scrapeIMG" -> {
                String[] args = new String[newCommand.getArguments().length];
                if(newCommand.getArguments().length >= 1){
                    args[0] = newCommand.getArguments()[0];
                }
                if(newCommand.getArguments().length >= 2){
                    args[1] = newCommand.getArguments()[1];
                }
                ImageScraper.main(args);
                return "Scraping IMG successful";
            }
            case "test" -> {
                return "Received!";
            }
            default -> {
                return "Command not found";
            }
        }
    }



}
