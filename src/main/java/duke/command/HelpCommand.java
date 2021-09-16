package duke.command;

import duke.exception.DukeException;

/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke
 *
 *
 * Description:
 * Class that encapsulates the help command inputted by the user
 *
 * @author Keith Tan
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    /**
     * Constructor for Help Command.
     * Initializes the help command
     */
    public HelpCommand() {

    }

    /**
     * Executes the help command. Prints the help message for the user.
     *
     * @throws DukeException throws an exception if any Duke Error occurs while running
     *                       the associated methods
     */
    @Override
    public String execute() throws DukeException {
        String helpString = "Here are the following commands Duke accepts:\n"
                + "todo - adds a todo task, type 'todo' followed by a description\n"
                + "event - adds a event task, type 'event' followed by a description and event duration\n"
                + "deadline - adds a deadline task, type 'deadline' followed by a description and event due date\n"
                + "list - shows the current task list\n"
                + "done - marks a task in the task list as complete, type 'done' followed by an integer\n"
                + "delete - deletes a task in the task list, type 'delete' followed by an integer\n"
                + "update -Update a task in the task list, etc 'update 2 desc <newdescription>, "
                + "fields: desc, sdt, edt\n"
                + "find - filter task lists for tasks through searching for a keyword\n"
                + "bye - exits the duke chat bot and saves all task in the task list to the hard disk";

        return helpString;

    }

}
