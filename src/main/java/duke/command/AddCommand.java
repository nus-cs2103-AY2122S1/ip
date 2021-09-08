package duke.command;

import duke.DukeException;
import duke.ExceptionType;
import duke.Storage;
import duke.TaskList;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
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
    public AddCommand(String[] strArr) throws DukeException {
        switch (strArr[0]) {
        case WORD_TODO:
            task = new ToDo(strArr[1]);
            break;
        case WORD_DEADLINE:
            task = new Deadline(strArr[1], strArr[2]);
            break;
        case WORD_EVENT:
            task = new Event(strArr[1], strArr[2]);
            break;
        default:
            throw new DukeException(ExceptionType.INVALID_COMMAND);
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
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
