package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Stops the chatbot.
 */
public class ExitCommand implements Command {
    public static final String COMMAND_IDENTIFIER = "bye";

    /**
     * Returns a Exit command.
     *
     * @return Xxit command.
     */
    public static Command create() {
        return new ExitCommand();
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.showExitMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
