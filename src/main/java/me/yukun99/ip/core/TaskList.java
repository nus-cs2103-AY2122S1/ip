package me.yukun99.ip.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javafx.scene.control.Label;
import me.yukun99.ip.exceptions.HelpBotDateTimeFormatException;
import me.yukun99.ip.exceptions.HelpBotInvalidTaskException;
import me.yukun99.ip.exceptions.HelpBotInvalidTaskTypeException;
import me.yukun99.ip.exceptions.HelpBotIoException;
import me.yukun99.ip.tasks.Task;

/**
 * Class to handle list of all tasks currently in the todo list.
 */
public class TaskList {
    // List of all tasks currently in the todo list.
    private final List<Task> taskList = new ArrayList<>();
    private final TaskFinder taskFinder;

    // Maps to store tasks by date.
    private final Map<DateTimePair, List<Task>> pairTaskMap = new HashMap<>();
    private final Map<Task, DateTimePair> taskPairMap = new HashMap<>();

    /**
     * Constructor for a TaskList instance.
     *
     * @param taskFinder TaskFinder instance for this TaskList.
     */
    public TaskList(TaskFinder taskFinder) {
        this.taskFinder = taskFinder;
    }

    /**
     * Adds a task to the TaskList.
     * Leave dateTimePair null if the task is a ToDo task.
     *
     * @param task Task to be added to the TaskList.
     * @param dateTimePair Updated DateTimePair to store with the Task.
     */
    public void addTask(Task task, DateTimePair dateTimePair) {
        taskList.add(task);
        task.updateFinder(taskFinder, false);
        if (dateTimePair != null) {
            updateDateTime(task, dateTimePair);
        }
    }

    /**
     * Gets task at specified index.
     *
     * @param strIndex String representing the index of the task in the TaskList.
     * @throws HelpBotInvalidTaskException If strIndex is not an Integer.
     */
    public Task getTask(String strIndex) throws HelpBotInvalidTaskException {
        try {
            int index = Integer.parseInt(strIndex) - 1;
            return taskList.get(index);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new HelpBotInvalidTaskException(e, "done", strIndex + "");
        }
    }

    /**
     * Updates the date of the task at specified index.
     *
     * @param strIndex String representing the index of the task in the TaskList.
     * @param date New date to be updated for the task.
     * @return Updated task.
     * @throws HelpBotInvalidTaskException If specified Task index does not exist.
     * @throws HelpBotInvalidTaskTypeException If specified Task is an instance of ToDo.
     * @throws HelpBotDateTimeFormatException If specified task date is not in the correct format.
     */
    public Task updateTask(String strIndex, String date)
            throws HelpBotInvalidTaskException, HelpBotInvalidTaskTypeException, HelpBotDateTimeFormatException {
        try {
            int index = Integer.parseInt(strIndex) - 1;
            Task task = taskList.get(index);
            task.updateDate(date);
            return task;
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new HelpBotInvalidTaskException(e, "update", strIndex + "");
        }
    }

    /**
     * Updates the stored DateTimePair of a specified task.
     * Stored values used to fetch the tasks by date.
     * Automatically called in addTask method.
     *
     * @param task Task to update stored DateTimePair for.
     * @param update Updated DateTimePair to be stored with the task.
     */
    public void updateDateTime(Task task, DateTimePair update) {
        try {
            DateTimePair pair = task.getDate();
            if (pairTaskMap.containsKey(pair)) {
                pairTaskMap.get(pair).remove(task);
            }
            List<Task> updateTasks;
            if (!pairTaskMap.containsKey(update)) {
                updateTasks = new ArrayList<>();
            } else {
                updateTasks = pairTaskMap.get(update);
            }
            pairTaskMap.put(update, updateTasks);
            updateTasks.add(task);
            taskPairMap.remove(task);
            taskPairMap.put(task, update);
        } catch (HelpBotInvalidTaskTypeException ignored) {
            // ignore error here because we will never want to call updateDateTime for ToDo tasks.
        }
    }

    /**
     * Gets the string representation of the tasks on a specified date.
     *
     * @param pair Date to get the string representation of tasks for.
     * @return String representation of the tasks on the specified date.
     */
    public String listByDate(DateTimePair pair) {
        StringBuilder message = new StringBuilder("\nHere are your tasks on " + pair.toString() + ".");
        List<Task> tasks = new ArrayList<>();
        for (Task task : taskPairMap.keySet()) {
            DateTimePair other = taskPairMap.get(task);
            if (other.hasEqualDate(pair)) {
                tasks.add(task);
            }
        }
        for (int i = 0; i < tasks.size(); ++i) {
            message.append("\n ").append(i + 1).append(".").append(tasks.get(i));
        }
        if (tasks.size() == 0) {
            message.append("\n  You don't have any tasks on ").append(pair).append(", idiot.");
        }
        return message.toString();
    }

    /**
     * Deletes a task from the TaskList.
     *
     * @param strIndex String representing the index of the task to be deleted.
     * @return Deleted task.
     * @throws HelpBotInvalidTaskException If specified Task index does not exist.
     */
    public Task deleteTask(String strIndex) throws HelpBotInvalidTaskException {
        try {
            int index = Integer.parseInt(strIndex) - 1;
            Task deleted = taskList.get(index);
            taskList.remove(index);
            deleted.updateFinder(taskFinder, true);
            return deleted;
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new HelpBotInvalidTaskException(e, "delete", strIndex);
        }
    }

    /**
     * Deletes multiple tasks from the TaskList.
     *
     * @param args Arguments specified by the user.
     * @return List containing all deleted tasks.
     * @throws HelpBotInvalidTaskException If specified Task index does not exist.
     */
    public List<Task> deleteTasks(String... args) throws HelpBotInvalidTaskException {
        List<Task> deletedTasks = new ArrayList<>();
        for (String strIndex : args) {
            try {
                int index = Integer.parseInt(strIndex) - 1;
                deletedTasks.add(taskList.get(index));
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                throw new HelpBotInvalidTaskException(e, "delete", strIndex);
            }
        }
        for (Task task : deletedTasks) {
            taskList.remove(task);
            task.updateFinder(taskFinder, true);
        }
        return deletedTasks;
    }

    /**
     * Deletes all tasks from the TaskList
     *
     * @return List containing all deleted tasks.
     */
    public List<Task> deleteAll() {
        List<Task> deletedTasks = new ArrayList<>(taskList);
        taskList.clear();
        taskFinder.deleteAllTasks();
        return deletedTasks;
    }

    /**
     * Archives all tasks from the TaskList.
     *
     * @param storage Storage instance to save archived tasks to.
     * @return List of archived tasks.
     * @throws HelpBotIoException If tasks could not be archived.
     */
    public List<Task> archiveTasks(Storage storage, String... args)
            throws HelpBotIoException, HelpBotInvalidTaskException {
        List<Task> archivedTasks = new ArrayList<>();
        for (String strIndex : args) {
            try {
                int index = Integer.parseInt(strIndex) - 1;
                archivedTasks.add(taskList.get(index));
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                throw new HelpBotInvalidTaskException(e, "archive", strIndex);
            }
        }
        for (Task task : archivedTasks) {
            storage.archiveTask(task);
            taskList.remove(task);
            task.updateFinder(taskFinder, true);
        }
        return archivedTasks;
    }

    /**
     * Gets the number of tasks in the TaskList.
     *
     * @return Number of tasks in the TaskList.
     */
    public int getRemaining() {
        return taskList.size();
    }

    /**
     * Gets the string representation of the TaskList to be saved into a file.
     *
     * @return String representation of the TaskList to be saved into a file.
     */
    public String saveString() {
        StringBuilder result = new StringBuilder();
        for (Task task : taskList) {
            result.append(task.saveString()).append(System.lineSeparator());
        }
        return result.toString();
    }

    /**
     * Updates the task amount on the current task amount label.
     *
     * @param label Label to update task amount for.
     * @return Updated label with new task amount.
     */
    public Label updateTaskAmount(Label label) {
        int doneCount = 0;
        for (Task task : taskList) {
            if (task.isDone()) {
                ++doneCount;
            }
        }
        label.setText("Remaining Tasks: " + taskList.size() + System.lineSeparator() + "Completed tasks: " + doneCount);
        return label;
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
        return taskList.equals(taskList1.taskList) && taskFinder.equals(taskList1.taskFinder)
                && pairTaskMap.equals(taskList1.pairTaskMap) && taskPairMap.equals(taskList1.taskPairMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskList, taskFinder, pairTaskMap, taskPairMap);
    }

    /**
     * Gets the string representation of the TaskList.
     *
     * @return String representation of the TaskList.
     */
    @Override
    public String toString() {
        StringBuilder message = new StringBuilder();
        if (taskList.size() == 0) {
            message
                    .append("\n  Oh I'm sorry, were you expecting ME to make you a todo list, you lazy sod?")
                    .append("\n  Do it yourself, idiot.");
        }
        for (int i = 0; i < taskList.size(); ++i) {
            message.append("\n ").append(i + 1).append(".").append(taskList.get(i));
        }
        return message.toString();
    }
}
