package duke;

import duke.commands.*;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidInputException;

/**
 * User interface. Handles text for user interface.
 * @author Ruth Poh
 */
public class Ui {
    private final Parser parser;
    private static boolean isActivatedClearCommand;

    Ui() {
        parser = new Parser();
        isActivatedClearCommand = false;
    }

    /**
     * Executes command stated in string and saves taskList changes to storage.
     *
     * @param storage  Storage for storing TaskList data
     * @param taskList TaskList of tasks
     * @param str Command string
     * @return Message after executing command stated in string
     */
    public String getMessage(Storage storage, TaskList taskList, String str) {
        try {
            // splits input to parse for keywords.
            String[] strParse = str.split(" ");
            String command = this.parser.parseCommand(strParse[0]);
            if (isActivatedClearCommand) {
                if (command.equals("y")) {
                    //do nothing
                }
                isActivatedClearCommand = false;
                command = "n";
            }

            switch (command) {
            case "bye":
                // breaks loop, closes chatbot.
                ByeCommand bye = new ByeCommand(storage, taskList, strParse);
                return bye.execute();
                //Fallthrough
            case "help":
                // lists command list
                HelpCommand help = new HelpCommand(storage, taskList, strParse);
                return help.execute();

                //Fallthrough
            case "list":
                // lists history of current tasks.
                ListCommand list = new ListCommand(storage, taskList, strParse);
                return list.execute();
                //Fallthrough
            case "todo":
                // adds a Todo task to the list.
                TodoCommand todo = new TodoCommand(storage, taskList, strParse);
                return todo.execute();

                //Fallthrough
            case "deadline":
                // adds a deadline task to the list.
                DeadlineCommand deadline = new DeadlineCommand(storage,
                        taskList, strParse);
                return deadline.execute();
                //Fallthrough
            case "event":
                // adds an event to the list. pretty much like deadline.
                EventCommand event = new EventCommand(storage, taskList, strParse);
                return event.execute();
                //Fallthrough
            case "done":
                // marks a task as done.
                DoneCommand done = new DoneCommand(storage, taskList, strParse);
                return done.execute();
                //Fallthrough
            case "delete":
                // deletes corresponding task on list.
                 DeleteCommand delete = new DeleteCommand(storage, taskList, strParse);
                 return delete.execute();
                //Fallthrough
            case "find":
                FindCommand find = new FindCommand(storage, taskList, strParse);
                return find.execute();
                //Fallthrough
            case "clearall":
                ClearallCommand clearall = new ClearallCommand(storage, taskList, strParse);
                return clearall.execute();
                //Fallthrough
            case "y":
                ClearallConfirmCommand clearallConfirm = new ClearallConfirmCommand(storage,
                        taskList, strParse);
                return clearallConfirm.execute();
                //Fallthrough
            case "n":
                ClearallRejectCommand clearallRejectCommand = new ClearallRejectCommand(storage,
                        taskList, strParse);
                return clearallRejectCommand.execute();
            default:
                throw new InvalidInputException();
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Getter method for welcome message for Dukewu.
     * @return Welcome message for Dukewu
     */
    public static String getWelcomeMessage() {
        return ("Hewwo and welcomew tow Dukewu!\n" +
                "Dukewu aimws tow helpw youw orgwanise your wist of taskws.\n" +
                "Pwease typwe 'help' for mwore infow! Uwu!");
    }

    /**
     * Getter method for successful loading of data for Dukewu.
     * @return Successful loading of data message
     */
    public static String getLoadingSuccessfulMessage() {
        return ("Loadiwng file for you. . . Loaded!\n");
    }
}
