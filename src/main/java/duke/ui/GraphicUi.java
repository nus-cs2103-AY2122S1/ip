package duke.ui;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
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
     * Runs the Duke chat-bot for the GUI. Takes in a String of user input and outputs the corresponding
     * Duke response.
     * @param des the user input into the Duke chat-box.
     * @return String object representing the corresponding response from Duke.
     */
    public String run(String des) {
        TaskList tList = new TaskList();

        Storage.readFromFile(tList);

        Parser p = new Parser();

        String command = p.checkForKeyword(des);
        try {
            if (command == null) {
                throw new DukeException(des + " is not a recognised command\n"
                        + "Please refer to the available commands using the \"help\" command");
            } else {
                Command c = null;
                if (command.equals("bye")) {
                    c = new ByeCommand();
                    return c.execute(des, tList);
                }

                if (command.equals("help")) {
                    c = new HelpCommand();
                }

                if (command.equals("list")) {
                    c = new ListCommand();
                }

                if (command.equals("done")) {
                    c = new DoneCommand();
                }

                if (command.equals("deadline")) {
                    c = new DeadlineCommand();
                }

                if (command.equals("event")) {
                    c = new EventCommand();
                }

                if (command.equals("todo")) {
                    c = new ToDoCommand();
                }

                if (command.equals("delete")) {
                    c = new DeleteCommand();
                }

                if (command.equals("find")) {
                    c = new FindCommand();
                }

                return c.execute(des, tList);
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}

