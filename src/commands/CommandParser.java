package commands;

public class CommandParser {
    private String command;
    private String input;

    public CommandParser(String input) {
        this.input = input;
        String[] array = input.split(" ");
        this.command = array[0];
    }

    public String getCommand() {
        return this.command;
    }
}
