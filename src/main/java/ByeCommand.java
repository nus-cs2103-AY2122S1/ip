package main.java;

public class ByeCommand extends Command {

    protected String input;

    ByeCommand(String input) {
        this.input = input;
    }

    public String reply() {
        return "-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n"
                + "Bye... Hope to see you again soon!\n"
                + "-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n";
    }
}
