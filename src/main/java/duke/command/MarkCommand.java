package duke.command;

import duke.exception.DukeException;
import duke.exception.MismatchedFormException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents the class about how to mark the task.
 */
public class MarkCommand extends Command {
    private String response;
    private int splitIndex;

    /**
     * Marks the command at splitIndex as done.
     *
     * @param response The user input.
     * @param splitIndex The index where done and digit is split.
     */
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
     * @throws DukeException The exceptions related to duke.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (!Parser.checkIsDigit(response.substring(splitIndex))) {
            throw new MismatchedFormException("done", "integer");
        }
        int taskNumber = Integer.parseInt(response.substring(5)); //from index 5 onwards it's task number.
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
