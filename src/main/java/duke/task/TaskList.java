package duke.task;

import duke.main.DukeException;
import duke.main.Ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Represents a collection of tasks.
 */
public class TaskList {
    private List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructor for a TaskList.
     *
     * @param taskList list of Tasks.
     */
    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Enumerates the list of tasks.
     *
     * @param taskList to enumerate
     * @return String enumeration of taskList.
     */
    public static String enumerateTasks(List<Task> taskList) {
        String toPrint = "";
        for (int i = 0; i < taskList.size(); i++) {
            int index = i + 1;
            toPrint += ("  " + index + ". " + taskList.get(i));
        }
        return toPrint;
    }

    /**
     * Adds a task to TaskList.
     *
     * @param task Task to be added.
     */
    public String addTask(Task task) {
        this.taskList.add(task);
        return Ui.getAddTaskMessage(task, this);
    }

    /**
     * Generate a summary of the TaskList.
     *
     * @return String summary of TaskList.
     */
    public String getTaskListSummary() {
        int numTasks = this.taskList.size();
        String size = numTasks == 0 ? "no" : String.valueOf(numTasks);
        String maybePlural = numTasks == 1 ? " task " : " tasks ";
        return "You have " + size + maybePlural + "in the list.\n";
    }

    /**
     * Remove a task from the TaskList.
     *
     * @param task Task to remove.
     */
    public String deleteTask(Task task) {
        taskList.remove(task);
        assert !taskList.contains(task);
        return Ui.getRemoveTaskMessage(task, this);
    }

    /**
     * Finds tasks that match any keyword.
     *
     * @param query for matching Tasks.
     * @return List of matching Tasks.
     */
    public List<Task> findTasks(String query) {
        if (query.isEmpty()) {
            throw new DukeException("You haven't specified any keywords\n");
        }
        String[] keywords = query.split(" ");
        List<Task> matches = new ArrayList<>();
        for (Task task : taskList) {
            if (task.containsKeyword(keywords)) {
                matches.add(task);
            }
        }
        return matches;
    }

    /**
     * Generates a formatted string for storing TaskList.
     *
     * @return String for storing TaskList.
     */
    public List<String> formatForStorage() {
        return taskList.stream().map(Task::generateStorageString).collect(Collectors.toList());
    }

    /**
     * Checks if the TaskList is empty.
     *
     * @return true if TaskList is empty, else false.
     */
    public boolean isEmpty() {
        return this.taskList.size() == 0;
    }

    @Override
    public String toString() {
        return enumerateTasks(taskList);
    }

    /**
     * Clear the TaskList.
     */
    public String clearTasks() {
        taskList.clear();
        return Ui.getClearTasksMessage();
    }

    /**
     * Get a Task from the TaskList.
     *
     * @param taskNum index of Task in TaskList.
     * @return Task that is retrieved.
     */
    public Task getTask(int taskNum) {
        if (taskNum > this.taskList.size() || taskNum < 1) {
            return null;
        }
        return taskList.get(taskNum - 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TaskList taskList1 = (TaskList) o;
        return taskList.equals(taskList1.taskList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskList);
    }
}
