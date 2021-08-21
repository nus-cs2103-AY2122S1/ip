package tiger.parser;

public class Parser {

    String command;

    public Parser(String command) {
        this.command = command;
    }

    // TODO: rewrite parsing functionality with regrex

    public String getCommandKeyword() {
        String[] array = this.command.split(" ");
        return array[0];
    }

}
