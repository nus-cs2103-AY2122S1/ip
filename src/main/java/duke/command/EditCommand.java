package duke.command;

import duke.Action;
import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.StringUtils;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;

public class EditCommand extends Command {
    /** The index of the task. */
    private final int index;
    /** The new description */
    private final String description;

    /**
     * Constructs an edit command using the given index and new description.
     *
     * @param action The given action type.
     * @param index The given index.
     * @param description The given new description.
     */
    public EditCommand(Action action, int index, String description) {
        super(action, false);
        this.index = index;
        this.description = description;
    }


    /**
     * Executes the command and prints the result in console.
     *
     * @param taskList The task list of duke.
     * @param storage  The local storage of duke.
     */
    @Override
    public void executeAndShow(TaskList taskList, Storage storage) {
        Ui.showMultiLines(execute(taskList, storage));
    }

    /**
     * Returns the result of executing the command.
     *
     * @param taskList The task list of duke.
     * @param storage  The local storage of duke.
     * @return A string representation of the result.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        Task temp = taskList.getTask(index);
        if (description.contains("/t")) {
            if (temp instanceof Deadline) {
                Deadline d = (Deadline) temp;
                String[] arr = Parser.parseEditInfo(description);
                assert arr.length == 2 : "Parse edit info error";
                d.setDeadlineTime(Parser.parseDateTime(arr[1]));
                if (!arr[0].equals("")) {
                    d.setDescription(arr[0]);
                }
                return StringUtils.getEditMessage(d);
            } else if (temp instanceof Event) {
                Event e = (Event) temp;
                String[] arr = Parser.parseEditInfo(description);
                assert arr.length == 2 : "Parse edit info error";
                e.setEventTime(Parser.parseDateTime(arr[1]));
                if (!arr[0].equals("")) {
                    e.setDescription(arr[0]);
                }
                return StringUtils.getEditMessage(e);
            } else {
                throw new DukeException("Todo task do not have a time");
            }
        } else {
            temp.setDescription(description);
            return StringUtils.getEditMessage(temp);
        }
    }
}
