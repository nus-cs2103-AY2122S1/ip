package petal.components;

import petal.Petal;
import petal.command.*;
import petal.exception.*;

import java.util.List;

public class Parser {

    private final Ui ui;
    private final TaskList taskList;
    private final Storage storage;

    public Parser(Storage storage, TaskList taskList) {
        this.ui = new Ui();
        this.storage = storage;
        this.taskList = taskList;
    }

    /**
     * Method that formats the message to be displayed. It splits the input and takes
     * the first word (assumed to be command if format followed) and reacts accordingly
     * @param message User input
     */
    public Command handleInput(String message) {
        message += " "; //So blank inputs can be handled
        String command = message.substring(0, message.indexOf(" "));
        String formatted = message.substring(message.indexOf(' ') + 1).trim();
        switch (command) { //Checks first word in string
            case "list":
                return new ListCommand();
            case "bye":
                return new ByeCommand();
            case "done":
                return new DoneCommand(formatted);
            case "delete":
                return new DeleteCommand(formatted);
            case "todo":
            case "deadline":
            case "event":
                return new TaskCommand(command, formatted);
            case "date":
                return new DateCommand(formatted);
            default:
                return new UnintelligibleCommand();
        }
    }

}
