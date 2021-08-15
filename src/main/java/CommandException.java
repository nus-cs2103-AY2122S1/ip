package main.java;

public class CommandException extends DukeException {
    public CommandException(String task, String command) {
        super(String.format("Your task of %s requires the command %s", task, command));
    }
}
