package duke.task;

import duke.DukeException;
import duke.Ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that encapsulates a list of tasks.
 */
public class TaskList {
    private final List<Task> tasks;
    private final Ui ui = new Ui();

    /**
     * Returns a new empty TaskList object.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns a new TaskList object with the given tasks.
     * @param tasks
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Gets the size of the task list.
     * @return The size of the task list.
     */
    public int getSize() {
        return this.tasks.size();
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Gets the task at the specified index.
     * @param taskNo Specified index.
     * @return The task at the specified index.
     */
    public Task getTask(int taskNo) {
        return this.tasks.get(taskNo - 1);
    }

    /**
     * Turns the task list into a String.
     * @return The String representation of the list to be saved to the hard disk.
     */
    public String saveList() {
        StringBuilder lst = new StringBuilder();
        for (int i = 1; i <= this.getSize(); i++) {
            lst.append(i).append(". ").append(this.getTask(i).toString()).append("\n");
        }
        return lst.toString();
    }

    /**
     * Marks a specified task as done.
     * @param taskNo The number of the task to be marked as done.
     */
    public void taskDone(int taskNo) {
        Task task = this.getTask(taskNo);
        task.setDone();
        ui.showTaskDone(task);
    }

    /**
     * Displays the add task message.
     * @param task The task to be added.
     */
    public void addTaskSuffix(Task task) {
        int taskNo = this.getSize();
        String t = taskNo == 1 ? " task " : " tasks ";
        ui.showAddTask(task, taskNo, t);
    }

    /**
     * Adds a task to the task list.
     * @param input The user input.
     * @throws DukeException If the input is invalid.
     */
    public void addTask(String input) throws DukeException {
        Task newTask;

        if (input.startsWith("todo ")) {
            newTask = new Todo(input);
        } else if (input.startsWith("deadline ") && input.contains("/by ")) {
            newTask = new Deadline(input);
        } else if (input.startsWith("event ") && input.contains("/at ")) {
            newTask = new Event(input);
        } else {
            throw new DukeException("OOPS!!! Invalid task description.");
        }

        this.getTasks().add(newTask);
        addTaskSuffix(newTask);

    }

    /**
     * Deletes a task from the task list.
     * @param taskNo The number of the task to be deleted.
     */
    public void deleteTask(int taskNo) {
        Task removedTask = this.getTasks().remove(taskNo - 1);
        int tasksLeft = this.getSize();
        String t = tasksLeft == 1 ? " task " : " tasks ";
        ui.showDeleteTask(removedTask, tasksLeft, t);
    }

    /**
     * Finds tasks occurring on a specified date.
     * @param date The specified date.
     */
    public void findTasksOnDate(LocalDate date) {
        List<Task> foundTasks = new ArrayList<>();
        for (Task task : this.getTasks()) {
            if (task instanceof Deadline) {
                Deadline d = (Deadline) task;
                if (d.getDueDate().isEqual(date)) {
                    foundTasks.add(d);
                }
            } else if (task instanceof Event) {
                Event e = (Event) task;
                if (e.getEventDate().isEqual(date)) {
                    foundTasks.add(e);
                }
            }
        }

        int num = foundTasks.size();
        ui.showTasksOnDate(date, num, foundTasks);
    }


}
