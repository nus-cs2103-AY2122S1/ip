package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Stops the chatbot.
 */
public class ExitCommand implements Command {
    public static final String COMMAND_IDENTIFIER = "bye";

    /**
     * Returns an Exit command.
     *
     * @return Exit command.
     */
    public static Command create() {
        return new ExitCommand();
    }

    @Override
    public String execute(TaskList tasks, Ui ui) {
        return ui.showExitMessage();
    }
}
