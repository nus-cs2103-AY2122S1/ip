package duke;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

import duke.exception.DukeException;
import duke.exception.IndexMismatchException;
import duke.exception.IndexOutOfBoundException;
import duke.task.Task;

/**
 * A class that represents a list of tasks.
 */
public class TaskList {
    /**
     * The list of tasks.
     */
    private final ArrayList<Task> taskList;

    /**
     * Constructs a {@code TaskList}.
     *
     * @param taskList The list of tasks.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns the number of tasks.
     *
     * @return The number of tasks.
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Gets the task with the specific index.
     *
     * @param index The index of the task.
     * @return The task with given index.
     * @throws IndexOutOfBoundException When the index is out of bound.
     */
    public Task getTask(int index) throws IndexOutOfBoundException {
        try {
            return this.taskList.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundException();
        }
    }

    /**
     * Adds a certain task to the list and print out the add message.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Removes a certain task from the list and print out the remove message.
     *
     * @param index The index of the task item to be removed.
     * @throws DukeException If the index is invalid.
     */
    public void removeTask(int index) throws DukeException {
        if (index <= 0) {
            throw new IndexMismatchException();
        }
        if (index > this.size()) {
            throw new IndexOutOfBoundException();
        }
        this.taskList.remove(index - 1);
    }

    /**
     * Marks a certain task in the list as done.
     *
     * @param item The index of the task item to be marked done.
     * @throws DukeException If the task is already done or the index is invalid.
     */
    public void completeTask(int item) throws DukeException {
        if (item <= 0) {
            throw new IndexMismatchException();
        }
        if (item > this.taskList.size()) {
            throw new IndexOutOfBoundException();
        }
        Task task = this.taskList.get(item - 1);
        if (task.isDone()) {
            throw new DukeException("OOPS!!! The task is already done!");
        }
        task.setDone(true);
    }

    /**
     * Undone a completed task in the list.
     *
     * @param item The index of the task item to be undone.
     * @throws DukeException If the task is not done yet or the index is invalid.
     */
    public void undoneTask(int item) throws DukeException {
        if (item <= 0) {
            throw new IndexMismatchException();
        }
        if (item > this.taskList.size()) {
            throw new IndexOutOfBoundException();
        }
        Task task = this.taskList.get(item - 1);
        if (!task.isDone()) {
            throw new DukeException("OOPS!!! The task is not done yet!");
        }
        task.setDone(false);
    }

    /**
     * Transforms the list to a string list and store it in the {@code StringBuilder}. If the list is empty, then it
     * will store the {@code emptyMessage} instead.
     *
     * @param builder      The {@code StringBuilder} to store the list.
     * @param emptyMessage The message to be stored when the list is empty.
     */
    private void buildList(StringBuilder builder, String emptyMessage) {
        if (this.taskList.isEmpty()) {
            builder.append(emptyMessage);
            return;
        }
        int listSize = this.taskList.size();
        for (int i = 0; i < listSize; i++) {
            builder.append("    ").append(i + 1).append(". ").append(this.taskList.get(i));
            if (i < listSize - 1) {
                builder.append("\n");
            }
        }
    }

    /**
     * Transforms the list to a string of task descriptions.
     *
     * @return A string of task descriptions.
     */
    public String printList() {
        StringBuilder itemList = new StringBuilder("Here are the tasks in your list:\n");
        this.buildList(itemList, "Nothing here yet...");

        return itemList.toString();
    }

    /**
     * Find out all tasks that is related to the given date and list them as a string.
     *
     * @param date A certain to filter the list.
     * @return A string of task description.
     */
    public String printList(LocalDate date) {
        ArrayList<Task> targetTasks = this.taskList.stream()
                .filter(x -> x.getDate() != null && x.getDate().equals(date))
                .collect(Collectors.toCollection(ArrayList::new));

        TaskList targetList = new TaskList(targetTasks);

        StringBuilder itemList = new StringBuilder("Here is the result:\n");
        targetList.buildList(itemList, "Nothing special will happen on this day");

        return itemList.toString();
    }

    /**
     * Find out all tasks that contains the given keyword and list them as a string.
     *
     * @param keyword A keyword to filter the list.
     * @return A string of task description.
     */
    public String printList(String keyword) {
        ArrayList<Task> targetTasks = this.taskList.stream()
                .filter(x -> x.getDescription().contains(keyword))
                .collect(Collectors.toCollection(ArrayList::new));

        TaskList targetList = new TaskList(targetTasks);

        StringBuilder itemList = new StringBuilder("Here is the result:\n");
        targetList.buildList(itemList, "Wow! Nothing matches your keyword.");

        return itemList.toString();
    }
}
