package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import task.Task;

import java.time.LocalDate;
import java.time.LocalTime;

public class AddCommand extends Command {
    /** The type of the task. **/
    public Task.TaskType type;

    /** A String representing the task's description. **/
    public String description;

    /** The date of the task. It can be null for some tasks. **/
    public LocalDate date;

    /** The time of the task. It can be null for some tasks. **/
    public LocalTime time;

    /**
     * A public constructor to initialize the AddCommand.
     *
     * @param type The type of the task.
     * @param description A String representing the description of the task.
     * @param date The date of the task.
     * @param time The time of the task.
     */
    public AddCommand(Task.TaskType type, String description, LocalDate date, LocalTime time) {
        this.type = type;
        this.description = description;
        this.date = date;
        this.time = time;
    }

    /**
     * A method to execute this AddCommand. When this method is executed,
     * new task will be added to the given TaskList, and message will be
     * send to the given Ui.
     *
     * @param taskList The given Duke TaskList.
     * @param ui The given Duke Ui.
     * @param storage The given Duke Storage.
     * @throws DukeException
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task newTask = taskList.add(this.type, this.description, this.date, this.time);
        ui.printMessage(new String[] {
                "Got it. I've added this task:",
                "  " + newTask.toString(),
                "Now you have " + taskList.amountOfTasks() + " tasks in the list."});
    }
}
