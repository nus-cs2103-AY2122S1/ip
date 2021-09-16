package duke.command;

import duke.*;
import duke.io.ResponseManager;
import duke.task.Task;
import duke.task.TaskManager;

public class SnoozeCommand implements ICommand {

    private final String input;
    private TaskManager tm;
    private ResponseManager responseManager;
    private Storage storage;
    private Task snoozedTask;
    private String reply;

    /**
     * Constructor for the command.
     *
     * @param input The user's input which triggered the creation of this command.
     */
    public SnoozeCommand(String input) {
        this.input = input;
    }

    /**
     * Changes the deadline/event time.
     *
     * @param tm The TaskManager object controlling the tasks in Duke.
     * @param responseManager The Ui object managing Duke's user interface.
     * @param storage The Storage object managing the local storing of tasks.
     */
    public void execute(TaskManager tm, ResponseManager responseManager, Storage storage) {
        try {
            snoozedTask = tm.snoozeTask(input);
            this.responseManager = responseManager;
            if (snoozedTask == null) {
                reply = responseManager.getInvalidIndexMessage();
            } else {
                reply = responseManager.getSnoozeTaskMessage(snoozedTask);
                storage.updateSave(tm.getTasks());
            }
        } catch (DukeException.InvalidInputException | DukeException.NoTimeSpecifiedException | DukeException.UnsnoozeableTaskException e) {
            reply = responseManager.getErrorMessage(e);
        }
    }

    public String getReply() {
        return reply;
    }
}
