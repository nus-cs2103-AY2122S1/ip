package seedu.duke.command;

import seedu.duke.Ui;
import seedu.duke.task.TaskList;

public class ExitCommand extends Command {
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";

    public ExitCommand(Ui ui, TaskList taskList) {
        super(ui, taskList);
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String getUsageMessage() {
        return "exit  | quit the chat bot";
    }

    /**
     * Prints Duke's exit message.
     */
    @Override
    public void execute() {
        ui.divide();
        ui.outputMessage(EXIT_MESSAGE);
        ui.divide();
    }

}
