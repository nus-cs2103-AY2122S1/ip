package duke.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import duke.gui.Ui;
import duke.util.DukeException;

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
        tasks = new ArrayList<>();
    }

    /**
     * Returns a new TaskList object with the given tasks.
     * @param tasks Given tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Gets the size of the task list.
     * @return The size of the task list.
     */
    public int getSize() {
        return tasks.size();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Gets the task at the specified index.
     * @param taskNo Specified index.
     * @return The task at the specified index.
     */
    public Task getTask(int taskNo) throws DukeException {
        try {
            return tasks.get(taskNo - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("You don't have that many tasks!");
        }
    }

    /**
     * Turns the task list into a String.
     * @return The String representation of the list to be saved to the hard disk.
     */
    public String saveList() throws DukeException {
        StringBuilder lst = new StringBuilder();
        for (int i = 1; i <= getSize(); i++) {
            lst.append(i).append(". ").append(getTask(i).toString()).append("\n");
        }
        return lst.toString();
    }

    /**
     * Marks a specified task as done.
     * @param taskNo The number of the task to be marked as done.
     */
    public String markTaskDone(int taskNo) throws DukeException {
        assert getTasks() != null : "Tasks not initiated";
        Task task = getTask(taskNo);
        task.setDone();
        return ui.showTaskDone(task);
    }

    /**
     * Adds a task to the task list.
     * @param input The user input.
     * @throws DukeException If the input is invalid.
     */
    public String addTask(String input) throws DukeException {
        assert getTasks() != null : "Tasks not initiated";
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

        if (isDuplicate(newTask)) {
            return ui.showDuplicate();
        }
        getTasks().add(newTask);
        return ui.showAddTask(this, newTask);

    }

    /**
     * Deletes a task from the task list.
     * @param taskNo The number of the task to be deleted.
     */
    public String deleteTask(int taskNo) throws DukeException {
        try {
            assert getTasks() != null : "Tasks not initiated";
            Task removedTask = getTasks().remove(taskNo - 1);
            int tasksLeft = getSize();
            String t = tasksLeft == 1 ? " task " : " tasks ";
            return ui.showDeleteTask(removedTask, tasksLeft, t);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("You don't have that many tasks!");
        }
    }

    /**
     * Finds tasks occurring on a specified date.
     * @param date The specified date.
     */
    public String findTasksOnDate(LocalDate date) {
        assert getTasks() != null : "Tasks not initiated";

        List<Task> foundTasks = new ArrayList<>();
        for (Task task : getTasks()) {
            if (task instanceof Deadline && ((Deadline) task).getDueDate().isEqual(date)) {
                foundTasks.add(task);
            } else if (task instanceof Event && ((Event) task).getEventDate().isEqual(date)) {
                foundTasks.add(task);
            }
        }

        int num = foundTasks.size();
        return ui.showTasksOnDate(date, num, foundTasks);
    }

    /**
     * Checks if two tasks are duplicates.
     * @param task The other task to compare to.
     * @return If the two tasks are duplicates.
     */
    public boolean isDuplicate(Task task) {
        return tasks.contains(task);
    }
}
