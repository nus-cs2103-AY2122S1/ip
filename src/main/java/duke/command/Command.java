package duke.command;

import duke.Duke;
import duke.exception.DukeException;
import duke.task.TaskList;

import java.io.IOException;
import java.util.Scanner;
/**
 * Represents a general user command.
 */
public abstract class Command {
    protected Duke duke;
    protected Scanner sc;

    /**
     * Constructor for Command.
     * @param duke
     * @param sc
     */
    public Command(Duke duke, Scanner sc) {
        this.duke = duke;
        this.sc = sc;
    }

    /**
     * Executes the command, depending on the child type.
     * @param taskList task list of the user that the chat bot has stored.
     * @throws IOException if there is an error in saving the file.
     * @throws DukeException if there is any internal Duke chatbot related errors.
     */
    public abstract void execute(TaskList taskList) throws IOException, DukeException;
}
