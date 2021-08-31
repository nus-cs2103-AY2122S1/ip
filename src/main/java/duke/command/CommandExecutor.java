package duke.command;

import duke.exceptions.CommandDoesNotExist;
import duke.exceptions.DukeExceptions;
import duke.parser.Parser;

import java.io.IOException;
import java.util.Scanner;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class CommandExecutor {

    private final Command command;
    private final Ui ui;
    private final Storage storage;
    private final TaskList taskList;

    public CommandExecutor(Storage storage, Ui ui, TaskList taskList) {
        this.command = new Command(storage, ui);
        this.ui = ui;
        this.storage = storage;
        this.taskList = taskList;
    }

    /**
     * This function is the body of the chat bot, and contains the flow and commands.
     */
    public String execute(String userCommand) throws DukeExceptions, IOException {
        ui.showWelcome();
        String cmd; // this is the container for the first word of the command received from the user

        Parser parser = new Parser(userCommand);
        cmd = parser.getFirstWord();

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

        } else if (cmd.equals("find")) {
            return command.find(userCommand, taskList);

            // Else, an item has been added to the chat bot
            // Commands are either todo, deadline or event
        } else if (cmd.equals("todo")) {
            return command.addTodo(userCommand, cmd, taskList);

        } else if (cmd.equals("deadline")) {
            return command.addDeadline(userCommand, cmd, taskList);

        } else if (cmd.equals("event")) {
            return command.addEvent(userCommand, cmd, taskList);

        } else {
            throw new CommandDoesNotExist(userCommand);
        }
    }
}
