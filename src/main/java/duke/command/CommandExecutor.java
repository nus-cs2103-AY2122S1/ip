/**
 * This function interprets the user command and invokes the corresponding commands.
 *
 * @author Megan Wee Rui En
 * @version CS2103T AY21/22 Semester 1
 */

package duke.command;

import java.io.IOException;

import duke.exceptions.CommandDoesNotExist;
import duke.exceptions.DukeExceptions;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class CommandExecutor {

    private final Command command;
    private final Ui ui;
    private final TaskList taskList;

    /**
     * Contructs a CommandExecutor object.
     *
     * @param storage Handles the storage and retrieval of information from a file.
     * @param ui Handles user interface.
     * @param taskList Handles the list of tasks.
     */
    public CommandExecutor(Storage storage, Ui ui, TaskList taskList) {
        this.command = new Command(storage, ui);
        this.ui = ui;
        this.taskList = taskList;
    }

    /**
     * Interprets the user input and executes the corresponding commands.
     *
     * @param userCommand The user input.
     * @return A String for the robot to output in response to the user input.
     * @throws DukeExceptions If there are errors with processing the user input.
     * @throws IOException If there are errors processing the file.
     */
    public String execute(String userCommand) throws DukeExceptions, IOException {
        assert userCommand != null : "The user command is empty or not passed through properly";
        Parser parser = new Parser(userCommand);
        String cmd = parser.getFirstWord(); // the first word of the command received from the user

        // 'bye' : Ends the program
        if (userCommand.equals("bye") || cmd.equals("bye")) {
            return command.bye();

            // 'list' : Retrieves information from the hard drive and prints it
        } else if (cmd.equals("list")) {
            return command.list(taskList);

            // 'done [int]' : marks the corresponding number in the list as done
        } else if (cmd.equals("done")) {
            return command.done(userCommand, taskList);

            // 'delete [int]' : delete the corresponding number
        } else if (cmd.equals("delete")) {
            return command.delete(userCommand, taskList);

            // 'find' [String] : finds all matching instances and prints them
        } else if (cmd.equals("find")) {
            return command.find(userCommand, taskList);

        } else if (cmd.equals("tag")) {
            return command.tag(userCommand, taskList);

            // Else, an item has been added to the chat bot
            // Commands are either todo, deadline or event
        } else if (cmd.equals("todo")) {
            return command.addTodo(userCommand, taskList);

        } else if (cmd.equals("deadline")) {
            return command.addDeadline(userCommand, taskList);

        } else if (cmd.equals("event")) {
            return command.addEvent(userCommand, taskList);

            // If the command did not match the previous commands, throw an error
        } else {
            throw new CommandDoesNotExist(userCommand);
        }
    }
}
