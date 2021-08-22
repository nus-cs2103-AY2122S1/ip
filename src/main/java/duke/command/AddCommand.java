package duke.command;

import java.time.LocalDate;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

public class AddCommand extends Command {

    private String taskType;
    private String task;
    private LocalDate date;

    public AddCommand(String taskType, String task) {
        this.taskType = taskType;
        this.task = task;
    }

    public AddCommand(String taskType, String task, LocalDate date) {
        this.taskType = taskType;
        this.task = task;
        this.date = date;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // Adds a Task to the TaskList instance
        Task taskToBeAdded;
        if (taskType.equals("todo")) {
            taskToBeAdded = new Todo(task);
        } else if (taskType.equals("deadline")) {
            taskToBeAdded = new Deadline(task, date);
        } else {
            taskToBeAdded = new Event(task, date);
        }
        tasks.addTask(taskToBeAdded);

        // Displays a message indicating the task has been successfully added to the list
        String message = "Got it. I've added this task:\n" + "  " + taskToBeAdded + "\n";
        if (tasks.getNumberOfTasks() <= 1) {
            message += "Now you have " + tasks.getNumberOfTasks() + " task in the list.";
        } else {
            message += "Now you have " + tasks.getNumberOfTasks() + " tasks in the list.";
        }
        ui.showCommandDone(message);

        // Saves the current task list to the hard drive
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
