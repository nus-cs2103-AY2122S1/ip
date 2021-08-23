package duke;

public class Parser {
    private String command;

    public Parser(String command) {
        this.command = command;
    }

    public static String process(String command) {
        String[] words = command.split(" ");
        String init = words[0];
        return init;
    }

}
