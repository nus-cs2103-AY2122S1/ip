package duke.Command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.TaskList;
import duke.task.*;

public class AddCommand implements ICommand{
    private Task task;
    private static final String WORD_TODO     = "todo";
    private static final String WORD_DEADLINE = "deadline";
    private static final String WORD_EVENT    = "event";

    public AddCommand(String[] strArr) {
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
        if (o == this) return true;

        if (o != null && o.getClass() == this.getClass()) {
            return ((AddCommand) o).task.equals(this.task);
        }
        return false;
    }
}
