package duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.gui.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.util.DukeException;
import duke.util.Storage;

public class EditCommand extends Command {
    private final int taskNo;
    private final char fieldToEdit;
    private final String thingToChangeTo;

    /**
     * Constructor for a new EditCommand object.
     * @param taskNo The number of the task to edit.
     * @param fieldToEdit The field of the task to edit.
     * @param thingToChangeTo The new name or date to change to.
     * @throws DukeException If the task number is invalid.
     */
    public EditCommand(String taskNo, char fieldToEdit, String thingToChangeTo) throws DukeException {
        try {
            this.taskNo = Integer.parseInt(taskNo);
            this.fieldToEdit = fieldToEdit;
            this.thingToChangeTo = thingToChangeTo;
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS! Please enter a valid task number.");
        }
    }

    /**
     * Executes the command.
     *
     * @param tasks The list of tasks.
     * @param ui The Ui object.
     * @param storage The Storage object.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.getTask(taskNo);
        switch (fieldToEdit) {
        case 'n':
            return changeName(task, ui);
        case 'd':
            return changeDate(task, ui);
        default:
            throw new DukeException("Invalid input! "
                    + "Enter edit n to change the name of the task or edit d to change the date of the task!");
        }
    }

    /**
     * Changes the name of the given task.
     *
     * @param task The given task.
     * @param ui The Ui object.
     * @return String that shows that the task name was changed.
     */
    public String changeName(Task task, Ui ui) {
        task.setTaskName(thingToChangeTo);
        return ui.showChangedTask(task);
    }

    /**
     * Changes the date of the given task.
     *
     * @param task The given task.
     * @param ui The Ui object.
     * @return String that shows that the task date was changed.
     * @throws DukeException when a Todo is passed into the method.
     */
    public String changeDate(Task task, Ui ui) throws DukeException {
        if (task instanceof Todo) {
            throw new DukeException("Task does not have a date!");
        }

        try {
            if (task instanceof Event) {
                Event e = (Event) task;
                e.setEventDate(LocalDate.parse(thingToChangeTo));
            } else if (task instanceof Deadline) {
                Deadline d = (Deadline) task;
                d.setDeadlineDate(LocalDate.parse(thingToChangeTo));
            }
            return ui.showChangedTask(task);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format! Enter date as <yyyy-mm-dd>");
        }
    }

    /**
     * If the command is the exit command.
     *
     * @return True if it is the exit command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
