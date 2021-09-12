package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

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
    public TaskList tasksWithDateSame(String time) {
        TaskList currList = new TaskList();
        taskList.stream()
                .filter(currTask -> !(currTask instanceof Todo))
                .filter(currTask -> currTask.isTimeSame(time))
                .forEach(currList::addElement);
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
        taskList.stream()
                .filter(currTask -> currTask.contains(content))
                .forEach(currList::addElement);
        return currList;
    }

    public TaskList tasksWithinMonthOrDay(String type) {
        TaskList currList = new TaskList();
        String now = currentTime();
        taskList.stream()
                .filter(currTask -> !(currTask instanceof Todo))
                .filter(currTask -> currTask.isWithinMonthOrDay(now).equals(type))
                .forEach(currList::addElement);
        return currList;
    }

    /**
     * Shows all strings stored with indexing.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String end = "\n   ";
        String begin = "   Here are the tasks in your list:\n   ";
        sb.append(begin);
        for (int i = 0; i < count; i++) {
            if (i == count - 1) {
                end = "";
            }
            String out = ((i + 1)) + "." + this.elementToString(i) + end;
            sb.append(out);
        }
        return sb.toString();
    }

    public String currentTime() {
        LocalDate dateNow = LocalDate.now();
        return dateNow.format(formatForm());
    }

    public DateTimeFormatter formatForm() {
        return DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH);
    }
}

