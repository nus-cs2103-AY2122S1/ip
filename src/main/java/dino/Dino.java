package dino;

import dino.command.*;
import dino.exception.*;
import dino.task.TaskList;
import dino.util.Parser;
import dino.util.Storage;
import dino.util.Ui;

/**
 * Represents a Personal Assistant ChatBot name Dino
 * It helps the user to keep track of various things
 */
public class Dino {
    private final TaskList taskList;
    private final Storage storage;
    private final Ui ui;

    /**
     * Constructs a Dino object
     * Loads storage file from the path specified, if available
     */
    public Dino() {
        this.storage = new Storage("data/dino.txt");
        this.taskList = new TaskList(this.storage.loadStorage());
        this.ui = new Ui();
    }

    /**
     * Gets the response from the ChatBot based on input command
     * @param input the input command
     * @return the output message after processing command
     */
    public String getResponse(String input) {
        try {
            return processCommand(input);
        } catch (DinoException e) {
            return e.getMessage();
        }
    }

    /**
     * Processes the input command after parsing and returns the output message
     * @param command the input command
     * @return the output message after execution
     * @throws DinoException the general set of exceptions
     */
    public String processCommand(String command) throws DinoException {
        Parser.CMDTYPE cmdType = Parser.parse(command);
        switch (cmdType) {
        case TODO:
        case EVENT:
        case DEADLINE: {
            Command cmd = new AddTaskCommand(command, cmdType);
            return cmd.execute(storage, taskList);
        }
        case DONE:
        case DELETE:
        case EDIT: {
            Command cmd = new UpdateCommand(command, cmdType);
            return cmd.execute(storage, taskList);
        }
        case LIST: {
            Command cmd = new ListCommand();
            return cmd.execute(storage, taskList);
        }
        case FIND: {
            Command cmd = new FindCommand(command);
            return cmd.execute(storage, taskList);
        }
        case BYE: {
            return ui.exit(storage, taskList);
        }
        default: {
            throw new InvalidInputException();
        }
        }
    }
}
