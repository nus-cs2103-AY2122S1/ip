package main.java.duke.commands;
import java.io.IOException;

import main.java.duke.DukeException;
import main.java.duke.MainWindow;
import main.java.duke.Storage;
import main.java.duke.TaskList;
import main.java.duke.tasks.Deadline;
import main.java.duke.tasks.Event;
import main.java.duke.tasks.Task;
import main.java.duke.tasks.Todo;

/**
 * A command that adds a task to task list.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Constructs a new add task command with the given task.
     *
     * @param task the given task
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the add task command.
     *
     * @param tasks given list of tasks
     * @param gui given gui object
     * @param storage given storage object
     * @throws IOException input and output exception
     * @throws DukeException duke exception
     */
    public String execute(TaskList tasks, MainWindow gui, Storage storage) throws IOException, DukeException {
        if (this.task instanceof Todo) {
            storage.saveTaskToFile(task.toString());
            return addTask(this.task, tasks);
        } else if (this.task instanceof Deadline) {
            Deadline deadline = (Deadline) this.task;
            storage.saveTaskToFile(task.toString());
            return addTask(deadline, tasks);
        } else {
            Event event = (Event) this.task;
            //System.out.println(event.timeFormatted);
            storage.saveTaskToFile(task.toString());
            return addTask(event, tasks);
        }
    }

    /**
     * Adds the task to the tasks list.
     *
     * @param task the task to be added
     */
    private String addTask(Task task, TaskList tasks) {
        String message1 = ("Got it. I've added this task: \n");
        String message2 = task.toString();
        tasks.getTaskList().add(task);
        assert tasks.getTaskList().contains(task) : "Task list should contain current task";
        String taskForm;

        if (tasks.getTaskList().size() == 1) {
            taskForm = " task";
        } else {
            taskForm = " tasks";
        }
        String message3 = ("Now you have " + (tasks.getTaskList().size()) + taskForm + " in the list.");
        return message1 + message2 + message3;
    }
}
