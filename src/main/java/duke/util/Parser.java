package duke.util;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.exception.DukeException;

import java.util.List;

/**
 * Parser deals with making sense of the user input. Creates and returns a command object
 * with all the stored information
 */
public class Parser {

    private Storage storage;
    private TaskList taskList;

    /**
     * Basic constructor for Parser
     *
     * @param storage storage object
     * @param taskList tasklist object
     */
    public Parser(Storage storage, TaskList taskList) {
        this.storage= storage;
        this.taskList = taskList;
    }

    /**
     * Loads the previous state stored in the storageTxt into tasklist
     */
    public void loadTask() {
        try {
            List<String> prevState = storage.loadSaved();
            taskList.insertPast(prevState);
        } catch (DukeException e) {
            System.out.println(e.toString() + "\nPlease try again" );
        }
    }

    /**
     * Interprets the input and returns the appropriate command
     *
     * @param input user input
     * @return command returns command based on the user input
     * @throws DukeException for any errors caused
     */
    public Command parse(String input) throws DukeException {
        String[] parsedInput = input.trim().split(" ", 2);
        try {
            switch (parsedInput[0]) {
            case "bye":
                return new ByeCommand(storage, taskList);
                // collection of commands to be executed when "bye" is detected
            case "list":
                return new ListCommand(storage, taskList);
                // collection of commands to be executed when "list" is detected
            case "done":
                return new DoneCommand(storage, taskList, parsedInput[1]);
                // collection of commands to be executed when "done" is detected
            case "deadline":
                return new AddCommand(storage, taskList, parsedInput[1], "deadline");
                // collection of commands to be executed when "deadline" is detected
            case "todo":
                return new AddCommand(storage, taskList, parsedInput[1], "todo");
                // collection of commands to be executed
            case "event":
                return new AddCommand(storage, taskList, parsedInput[1], "event");
                // collection of commands to be executed when "event" is detected
            case "delete":
                return new DeleteCommand(storage, taskList, parsedInput[1]);
                // collection of commands to be executed when "delete" is detected
            case "find":
                return new FindCommand(storage, taskList, parsedInput[1]);
                // collection of commands to be executed when "find" is detected
            case "help":
                return new HelpCommand(storage, taskList);
                // collection of commands to be executed when "help" is detected
            default:
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            throw new DukeException("Sorry I can't understand what you've inputted. :(");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Missing info after action");
        }
    }
}
