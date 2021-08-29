package duke.task;

import java.util.ArrayList;

import duke.exception.OutOfRangeException;

public class TaskList {
    /** The data structure used to store the tasks. */
    private ArrayList<Task> taskList;

    /** Number of tasks stored. */
    private int count;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Sets up the list of tasks.
     *
     * @param taskList The list for the task.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
        count = taskList.size();
    }

    public int getSize() {
        return count;
    }

    /**
     * Removes the task at index place and returns it.
     *
     * @param place The index of task in task list.
     * @return The removed task.
     * @throws OutOfRangeException The exception related to index out of bounds.
     */
    public Task removeElement(int place) throws OutOfRangeException {
        Task shouldDelete;
        try {
            shouldDelete = taskList.get(place);
        } catch (IndexOutOfBoundsException e) {
            throw new OutOfRangeException("delete");
        }
        taskList.remove(place);
        count--;
        return shouldDelete;
    }

    /**
     * Adds the task to task list.
     *
     * @param task The task to be added.
     */
    public void addElement(Task task) {
        taskList.add(task);
        count++;
    }

    public String elementToString(int place) {
        return taskList.get(place).toString();
    }

    /**
     * Returns the task list of specific date.
     *
     * @param time The user input time.
     * @return The task list held at that time.
     */
    public TaskList tasksWithDate(String time) {
        TaskList currList = new TaskList();
        for (int i = 0; i < count; i++) {
            Task currTask = taskList.get(i);
            if (!(currTask instanceof Todo) && currTask.compareTime(time)) {
                currList.addElement(currTask);
            }
        }
        return currList;
    }

    /**
     * Returns the marked element.
     *
     * @param place The index of task in task list.
     * @return The task marked as done.
     * @throws OutOfRangeException The exception related to out of bounds.
     */
    public Task markElement(int place) throws OutOfRangeException {
        Task shouldMark;
        try {
            shouldMark = taskList.get(place);
        } catch (IndexOutOfBoundsException e) {
            throw new OutOfRangeException("done");
        }
        shouldMark.markAsDone();
        return shouldMark;
    }

    /**
     * Returns the tasks match that content.
     *
     * @param content The user input.
     * @return The list of tasks.
     */
    public TaskList tasksWithContent(String content) {
        TaskList currList = new TaskList();
        for (int i = 0; i < count; i++) {
            Task currTask = taskList.get(i);
            if (currTask.contains(content)) {
                currList.addElement(currTask);
            }
        }
        return currList;
    }
}
