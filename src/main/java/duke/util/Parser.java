package duke.util;

import duke.command.*;
import duke.exception.*;

import java.util.List;

/**
 * Parser deals with making sense of the user input. Creates and returns a command object
 * with all the stored information
 */

public class Parser {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Parser(Storage storage, TaskList taskList, Ui ui) {
        this.storage = storage;
        this.taskList = taskList;
        this.ui = ui;
    }

    public void loadTask() {
        try {
            List<String> prevState = storage.loadSaved();
            taskList.insertPast(prevState);
        } catch (DukeException e) {
            ui.error(e.toString());
        }
    }




    /**
     * Parses the input and returns command to be executed
     */
    public Command parse(String input) throws DukeException {
        String[] parsedInput = input.trim().split(" ", 2);
        try {
            switch (parsedInput[0]) {
            case "bye":
                return new ByeCommand(storage, taskList, ui);
            case "list":
                return new ListCommand(storage, taskList, ui);
            case "done":
                return new DoneCommand(storage, taskList, ui, parsedInput[1]);
            case "deadline":
                return new AddCommand(storage, taskList, ui, parsedInput[1], "deadline");
            case "todo":
                return new AddCommand(storage, taskList, ui, parsedInput[1], "todo");
            case "event":
                return new AddCommand(storage, taskList, ui, parsedInput[1], "event");
            case "delete":
                return new DeleteCommand(storage, taskList, ui, parsedInput[1]);
            default:
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            throw new DukeException("User input an invalid action.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Missing info after action");
        }
    }
}
