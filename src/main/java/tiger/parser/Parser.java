package tiger.parser;

public class Parser {

    String command;

    public Parser(String command) {
        this.command = command;
    }

    public String getCommandKeyword() {
        String[] array = this.command.split(" ");
        return array[0];
    }
}
