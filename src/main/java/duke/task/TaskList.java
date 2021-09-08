package duke.task;

import duke.exceptions.DukeException;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> TASKS;

    private final String ERROR_OUT_OF_INDEX = "Error: Task out of list. Use 'list' to check your task index.";

    /**
     * Constructor for an empty TaskList.
     */
    public TaskList() {
        this.TASKS = new ArrayList<>();
    }

    /**
     * Constructor for TaskList.
     *
     * @param tasks Input tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.TASKS = tasks;
    }

    /**
     * Returns the duke.task at the taskIndex.
     *
     * @param taskIndex taskIndex starts the list from 1.
     * @return Task at taskIndex in the list of tasks.
     * @throws DukeException when taskIndex is bigger than length of list of tasks.
     */
    public Task getTask(int taskIndex) throws DukeException {
        try {
            return TASKS.get(taskIndex - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(ERROR_OUT_OF_INDEX);
        }
    }

    /**
     * Adds duke.task at end of the list of tasks.
     *
     * @param task Input duke.task.
     */
    public void addTask(Task task) {
        TASKS.add(task);
    }

    /**
     * Adds duke.task to the list of tasks.
     *
     * @param task Input duke.task.
     * @param taskIndex taskIndex in the duke.task list to add the duke.task to.
     */
    public void addTask(Task task, int taskIndex) {
        TASKS.add(taskIndex - 1, task);
    }

    /**
     * Removes and returns duke.task at input taskIndex.
     *
     * @param taskIndex taskIndex starts the list from 1.
     * @return removed Task object.
     * @throws DukeException when taskIndex is bigger than length of list of tasks.
     */
    public Task removeTask(int taskIndex) throws DukeException {
        try {
            return TASKS.remove(taskIndex - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(ERROR_OUT_OF_INDEX);
        }
    }

    /**
     * Removes input duke.task from the list.
     *
     * @param task Input duke.task.
     */
    public void removeTask(Task task) {
        TASKS.remove(task);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return number of tasks in the list.
     */
    public int length() {
        return TASKS.size();
    }

    /**
     * Returns the String representing the list of tasks to be written into the file.
     *
     * @param delimiter Input delimiter.
     * @param done Identifier for done.
     * @param notDone Identifier for not done.
     * @return String to be written into file.
     */
    public String getFileString(String delimiter, String done, String notDone) {
        StringBuilder builder = new StringBuilder();
        for (Task task : TASKS) {
            String appendedString = task.getTaskFileString(delimiter, done, notDone) + "\n";
            builder.append(appendedString);
        }
        return builder.toString();
    }

    /**
     * Returns a new TaskList containing tasks with description containing input String.
     *
     * @param string Input String.
     * @return new TaskList containing all tasks with description containing input String.
     */
    public TaskList findTasksContainingString(String string) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : TASKS) {
            if (task.checkDescriptionContains(string)) {
                matchingTasks.add(task);
            };
        }
        return new TaskList(matchingTasks);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < TASKS.size(); i++) {
            builder.append(i + 1).append(" ").append(TASKS.get(i).toString()).append("\n");
        }
        return builder.toString();
    }
}
