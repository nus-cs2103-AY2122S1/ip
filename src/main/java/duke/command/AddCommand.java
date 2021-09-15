package duke.command;

import static java.util.Objects.requireNonNull;

import duke.DukeException;
import duke.ExceptionType;
import duke.Storage;
import duke.TaskList;
import duke.task.Task;
import duke.ui.Ui;

public class AddCommand implements ICommand {
    private static final String WORD_TODO = "todo";
    private static final String WORD_DEADLINE = "deadline";
    private static final String WORD_EVENT = "event";
    private final Task task;

    /**
     * Initialize the task to add.
     * @param strArr Info of the task to add.
     */
    public AddCommand(String[] strArr) throws IllegalArgumentException {
        requireNonNull(strArr, "Input string array is null");

        if (strArr.length == 2 && strArr[0].equals(WORD_TODO)) {
            task = Task.getToDo(strArr[1]);
        } else if (strArr.length == 3 && strArr[0].equals(WORD_DEADLINE)) {
            task = Task.getDeadline(strArr[1], strArr[2]);
        } else if (strArr.length == 3 && strArr[0].equals(WORD_EVENT)) {
            task = Task.getEvent(strArr[1], strArr[2]);
        } else {
            throw new IllegalArgumentException("Input string array is shorter than required.");
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        requireNonNull(taskList);
        requireNonNull(ui);
        requireNonNull(storage);

        if (taskList.hasDuplicate(task)) {
            throw new DukeException(ExceptionType.HAS_DUPLICATE);
        }

        taskList.add(task);
        Ui.printNewTask(task.toString());
        Ui.printTaskCount(taskList.size());
        storage.writeLine(task.populateSaveData() + System.lineSeparator());
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o != null && o.getClass() == this.getClass()) {
            return ((AddCommand) o).task.equals(this.task);
        }
        return false;
    }
}
