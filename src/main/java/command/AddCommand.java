package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import dukeException.DukeException;
import task.Task;

import java.time.LocalDate;
import java.time.LocalTime;

public class AddCommand extends Command {
    private Task.taskType type;
    private String description;
    private LocalDate date;
    private LocalTime time;

    public AddCommand(Task.taskType type, String description, LocalDate date, LocalTime time) {
        this.type = type;
        this.description = description;
        this.date = date;
        this.time = time;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task newTask = taskList.add(this.type, this.description, this.date, this.time);
        ui.printMessage(new String[] {
                "Got it. I've added this task:",
                "  " + newTask.toString(),
                "Now you have " + taskList.amountOfTasks() + " tasks in the list."});
    }
}
