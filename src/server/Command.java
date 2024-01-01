package server;

import java.util.ArrayList;
import java.util.List;

public class Command{
    private String command;
    private String[] arguments;
    public String getCommand() {
        return command;
    }
    public void setCommand(String command) {
        this.command = command;
    }
    public String[] getArguments() {
        return arguments;
    }
    public void setArguments(String[] arguments) {
        this.arguments = arguments;
    }
    public Command(String command, String[] arguments) {
        setCommand(command);
        setArguments(arguments);
    }
    public  Command(){
        command = null;
        arguments = null;
    }

    public Command convert(String input) {
        List<String> tokens = new ArrayList<>();
        StringBuilder curr = new StringBuilder();
        for (char c : input.trim().toCharArray()) {
            if (c == ' ') {
                tokens.add(curr.toString());
                curr.delete(0, curr.length());
            } else {
                curr.append(c);
            }
        }
        tokens.add(curr.toString());

        this.command = tokens.get(0);
        this.arguments = tokens.subList(1, tokens.size()).toArray(new String[0]);
        return this;
    }
}
