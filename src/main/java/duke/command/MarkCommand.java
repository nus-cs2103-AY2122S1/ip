package duke.command;

import duke.exception.DukeException;
import duke.exception.MismatchedFormException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class MarkCommand extends Command {
    private String response;
    private int splitIndex;

    public MarkCommand(String response, int splitIndex) {
        this.response = response;
        this.splitIndex = splitIndex;
    }

    /**
     * Shows the task just be marked as done.
     *
     * @param tasks The list of tasks.
     * @param ui The user interaction instance.
     * @param storage The instance to store data.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (!Parser.checkIsDigit(response.substring(splitIndex))) {
            throw new MismatchedFormException("done", "integer");
        }
        int taskNumber = Integer.parseInt(response.substring(5));
        Task shouldMark = tasks.markElement(taskNumber - 1);
        String stringForm = shouldMark.toString();
        storage.replace(taskNumber - 1, tasks);
        return ui.showDone(stringForm);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
