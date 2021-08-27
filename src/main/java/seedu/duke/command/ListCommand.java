package seedu.duke.command;

import seedu.duke.Ui;
import seedu.duke.task.TaskList;

public class ListCommand extends Command {
    private static final String LIST_MESSAGE = "Here are the tasks in your list:\n";

    public ListCommand(Ui ui, TaskList taskList) {
        super(ui, taskList);
    }

    @Override
    public String getUsageMessage() {
        return "list  | get the current list of tasks";
    }

    /**
     * Prints the current tasks in the task list.
     */
    @Override
    public void execute() {

        ui.divide();
        ui.outputMessage(LIST_MESSAGE + taskList.toString());
        ui.divide();
    }

}

