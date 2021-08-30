package duke.command;

import duke.task.TaskList;

/**
 * Command to close the application
 */
public class ExitCommand extends Command {
    public ExitCommand() {
        setCommandString("bye");
    }

    /**
     * Exits the application.
     *
     * @param input Full user input
     * @param taskList The list of tasks
     * @return The response
     */
    @Override
    public String parse(String input, TaskList taskList) {
        System.exit(0);
        return "Goodbye, hope to see you again!";
    }
}
