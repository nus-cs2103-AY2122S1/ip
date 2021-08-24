package duke.task;

import duke.exception.OutOfRangeException;

import java.util.ArrayList;

public class TaskList {
    /** The data structure used to store the tasks. */
    private ArrayList<Task> taskList;

    /** Number of tasks stored. */
    private int count;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
        count = taskList.size();
    }

    public int getSize() {
        return count;
    }

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

    public void addElement(Task task) {
        taskList.add(task);
        count++;
    }

    public String elementToString(int place) {
        return taskList.get(place).toString();
    }

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
