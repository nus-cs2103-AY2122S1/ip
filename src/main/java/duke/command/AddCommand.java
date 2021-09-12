package duke.command;

import java.io.IOException;
import java.time.LocalDate;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * This AddCommand class represents a command to add a task to the task list.
 */
public class AddCommand extends Command {

    private String taskType;
    private String task;
    private LocalDate date;

    /**
     * Constructor for an AddCommand instance that takes in a task type and task.
     *
     * @param taskType The type of the task to be added.
     * @param task The description of the task to be added.
     */
    public AddCommand(String taskType, String task) {
        this.taskType = taskType;
        this.task = task;
    }

    /**
     * Constructor for an AddCommand instance that takes in a task type, task and date.
     *
     * @param taskType The type of the task to be added.
     * @param task The description of the task to be added.
     * @param date The date in which the task is due by / held on.
     */
    public AddCommand(String taskType, String task, LocalDate date) {
        this.taskType = taskType;
        this.task = task;
        this.date = date;
    }

    /**
     * Adds a task to the task list and updates the hard disk of the change.
     *
     * @param tasks The task list.
     * @param storage The storage system of the application.
     * @return Completion message of this command.
     * @throws IOException If the data cannot be saved in the file.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws IOException {
        // Adds the correct Task instance to the TaskList instance
        Task taskToBeAdded;
        if (taskType.equals("todo")) {
            taskToBeAdded = new Todo(task);
        } else if (taskType.equals("deadline")) {
            taskToBeAdded = new Deadline(task, date);
        } else {
            taskToBeAdded = new Event(task, date);
        }
        tasks.addTask(taskToBeAdded);

        // Constructs a message indicating the task has been successfully added to the list
        String message = "Got it. I've added this task:\n" + "  " + taskToBeAdded + "\nNow you have ";
        int numberOfTasks = tasks.getNumberOfTasks();
        assert numberOfTasks >= 0 : "Number of tasks should not be negative";
        if (numberOfTasks <= 1) {
            message += numberOfTasks + " task in the list.";
        } else {
            message += numberOfTasks + " tasks in the list.";
        }

        // Saves the updated task list to the hard drive
        storage.save(tasks);

        return message;
    }
}
