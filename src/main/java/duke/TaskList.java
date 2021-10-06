package duke;

import java.util.ArrayList;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * This class represents a {@code TaskList} which handles all the task
 * operations related to {@code Task}.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs a new {@code TaskList} with the given tasks.
     *
     * @param tasks An ArrayList of {@code Task}.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns all the tasks in the list.
     *
     * @return All tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the size of the tasks in the list.
     *
     * @return Integer representing the size of the list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the last task in the list.
     *
     * @return A {@code Task}.
     */
    public Task lastTask() {
        return tasks.get(tasks.size() - 1);
    }

    /**
     * Adds a {@code Todo} task into tasks.
     *
     * @param title Title of new {@code Todo}.
     */
    public void addTodoTask(String title) {
        tasks.add(new Todo(title));
    }

    /**
     * Adds a {@code Deadline} task into tasks.
     *
     * @param title    Title of new {@code Deadline}.
     * @param deadline Deadline of new {@code Deadline}.
     */
    public void addDeadlineTask(String title, String deadline) {
        tasks.add(new Deadline(title, deadline));
    }

    /**
     * Adds a {@code Event} task into tasks.
     *
     * @param title    Title of new {@code Event}.
     * @param deadline Deadline of new {@code Event}.
     */
    public void addEventTask(String title, String deadline) {
        tasks.add(new Event(title, deadline));
    }

    /**
     * Deletes a task at the given taskNo, starting at index 0.
     *
     * @param taskNo Index of task to be deleted.
     *
     * @return The deleted {@code Task}.
     * @throws DukeException Nothing in the list to be deleted, or task number out
     *                       of valid range.
     */
    public Task deleteTask(int taskNo) throws DukeException {
        if (tasks.isEmpty()) {
            throw new DukeException("Nothing in the list to delete!");
        }

        try {
            Task task = tasks.get(taskNo);
            tasks.remove(task);
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(String.format("Enter a valid number between 1 - %d", tasks.size()));
        }
    }

    /**
     * Marks a {@code Task} as done in the given task number, starting with index 0.
     *
     * @param taskNo Index of {@code Task} to be marked as done.
     *
     * @return A {@code Pair} containing the previous status of {@code Task} and the
     * {@code Task} at the given index.
     * @throws DukeException Nothing in the list to be marked, or task number out
     *                       of valid range.
     */
    public Pair<Boolean, Task> markTaskDone(int taskNo) throws DukeException {
        if (tasks.isEmpty()) {
            throw new DukeException("Nothing in the list!");
        }

        try {
            Task task = tasks.get(taskNo);
            return new Pair<Boolean, Task>(!task.markAsDone(), task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(String.format("Enter a valid number between 1 - %d", tasks.size()));
        }
    }

    /**
     * Filters tasks with title containing the given query keyword.
     *
     * @param query filter term.
     *
     * @return An arraylist of tasks with title containing the query keyword.
     */
    public ArrayList<Task> searchTasks(String query) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            if (task.contains(query)) {
                result.add(task);
            }
        }
        return result;
    }

    /**
     * Updates the date of a {@code Task} in the tasklist.
     *
     * @param taskNo Index of {@code Task} to be updated.
     * @param date   New date of the {@code Task}
     *
     * @return Updated Task
     * @throws DukeException Invalid taskNo
     */
    public Task updateDate(int taskNo, String date) throws DukeException {
        try {
            Task task = tasks.get(taskNo);
            return task.setDate(date);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(String.format("Enter a valid number between 1 - %d", tasks.size()));
        }
    }

    /**
     * Updates the title of a {@code Task} in the tasklist.
     *
     * @param taskNo Index of {@code Task} to be updated.
     * @param title  New title of the {@code Task}
     *
     * @return Updated Task
     * @throws DukeException Invalid taskNo
     */
    public Task updateTitle(int taskNo, String title) throws DukeException {
        try {
            Task task = tasks.get(taskNo);
            return task.setTitle(title);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(String.format("Enter a valid number between 1 - %d", tasks.size()));
        }
    }

    /**
     * Updates the title and date of a {@code Task} in the tasklist.
     *
     * @param taskNo Index of {@code Task} to be updated.
     * @param title  New title of the {@code Task}
     * @param date   New date of the {@code Task}
     * @return Updated Task
     * @throws DukeException Invalid taskNo
     */
    public Task updateDateTitle(int taskNo, String title, String date) {
        Task updatedDateTask = updateDate(taskNo, date);
        return updatedDateTask.setTitle(title);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof TaskList) {
            TaskList otherTaskList = (TaskList) other;
            return this.tasks.equals(otherTaskList.tasks);
        }
        return false;
    }
}
