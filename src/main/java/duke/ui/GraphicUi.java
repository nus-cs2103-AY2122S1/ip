package duke.ui;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoAfterCommand;
import duke.commands.DoneCommand;
import duke.commands.EventCommand;
import duke.commands.FindCommand;
import duke.commands.HelpCommand;
import duke.commands.ListCommand;
import duke.commands.ToDoCommand;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;

/**
 * GraphicUi class used to handle interactions with the user.
 * Contains methods that
 * (i) feeds the chat-bot input and returns corresponding output.
 */
public class GraphicUi {

    /**
     * Returns String representation of response from Duke. Runs the Duke chat-bot for the GUI. Takes in a String of user input and outputs the corresponding
     * Duke response.
     *
     * @param des User input into the Duke chat-box.
     * @return String object representing the corresponding response from Duke.
     */
    public String run(String des) {
        TaskList tList = new TaskList();

        Storage.readFromFile(tList);

        Parser p = new Parser();

        String keyword = p.checkForKeyword(des);
        try {
            if (keyword == null) {
                throw new DukeException(des + " is not a recognised command\n"
                        + "Please refer to the available commands using the \"help\" command");
            }
            Command c;
            switch (keyword) {
            case "bye":
                c = new ByeCommand();
                System.out.print(c.execute(des, tList));
                System.exit(0);
                break;
            case "help":
                c = new HelpCommand();
                break;
            case "list":
                c = new ListCommand();
                break;
            case "done":
                c = new DoneCommand();
                break;
            case "delete":
                c = new DeleteCommand();
                break;
            case "deadline":
                c = new DeadlineCommand();
                break;
            case "event":
                c = new EventCommand();
                break;
            case "todo":
                c = new ToDoCommand();
                break;
            case "doafter":
                c = new DoAfterCommand();
                break;
            case "find":
                c = new FindCommand();
                break;
            default:
                throw new DukeException("Error in parser. Ouput of parser not recognised.");
            }
            return c.execute(des, tList);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}

