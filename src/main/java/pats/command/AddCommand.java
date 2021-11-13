package pats.command;

import static java.util.Objects.requireNonNull;

import pats.ExceptionType;
import pats.Parser;
import pats.PatsException;
import pats.Storage;
import pats.TaskList;
import pats.task.Task;
import pats.ui.Ui;

public class AddCommand extends Command {
    private final Task task;

    /**
     * Initializes the task to add.
     *
     * @param strArr Info of the task to add.
     */
    public AddCommand(String[] strArr) throws IllegalArgumentException {
        requireNonNull(strArr, "Input string array is null");

        if (strArr.length == 2 && strArr[0].equals(Parser.getWordTodo())) {
            task = Task.getToDo(strArr[1]);
        } else if (strArr.length == 3 && strArr[0].equals(Parser.getWordDeadline())) {
            task = Task.getDeadline(strArr[1], strArr[2]);
        } else if (strArr.length == 3 && strArr[0].equals(Parser.getWordEvent())) {
            task = Task.getEvent(strArr[1], strArr[2]);
        } else {
            throw new IllegalArgumentException("Wrong input string array format");
        }
    }

    /**
     * Adds a task to task list and outputs information of the created task.
     *
     * @param taskList duke's task list
     * @param ui current Ui instance
     * @param storage current storage instance
     * @throws PatsException if there is an unfinished task which equals to the given task
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws PatsException {
        requireNonNull(taskList);
        requireNonNull(ui);
        requireNonNull(storage);

        if (taskList.hasDuplicate(task)) {
            throw new PatsException(ExceptionType.HAS_DUPLICATE);
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
