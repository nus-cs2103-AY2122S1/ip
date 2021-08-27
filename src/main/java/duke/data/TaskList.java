package duke.data;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

import duke.DukeException;
import duke.task.DateTimeTask;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class TaskList {

    /** ArrayList to store all tasks */
    private ArrayList<Task> items;
    /** Calendar object to store dates and times of timed tasks */
    private Calendar calendar;

    /**
     * Returns a new TaskList object when there is no previously saved data.
     * Item list and calendar initialized to be empty.
     */
    public TaskList() {
        this.items = new ArrayList<>();
        this.calendar = new Calendar();
    }

    /**
     * Returns a new TaskList object when there is previously saved data.
     * Converts saved data to Task objects and initializes item list and calendar with saved data.
     *
     * @param taskArrayList ArrayList of String arrays, each representing a task.
     * @throws DukeException If data is corrupt or missing information.
     * @throws FileNotFoundException If data file cannot be found.
     */
    public TaskList(ArrayList<String[]> taskArrayList) throws DukeException, FileNotFoundException {
        this.items = new ArrayList<>();
        this.calendar = new Calendar();
        for (String[] taskElements : taskArrayList) {
            Task t = null;
            switch (taskElements[0]) {
            case "T":
                t = new Todo(taskElements[2]);
                break;
            case "E":
                t = new Event(Arrays.copyOfRange(taskElements, 2, taskElements.length));
                calendar.add((DateTimeTask) t);
                break;
            case "D":
                t = new Deadline(Arrays.copyOfRange(taskElements, 2, taskElements.length));
                calendar.add((DateTimeTask) t);
                break;
            default:
                break;
            }
            if (t != null) {
                if (taskElements[1].equals("X")) {
                    t.markDone();
                }
                this.items.add(t);
            }
        }
    }

    /**
     * Adds a task to item list and storage.
     * If the task is a timed task, adds it to calendar as well.
     *
     * @param task Task to be added.
     * @param storage Storage object to store task data.
     */
    public void addItem(Task task, Storage storage) {
        this.items.add(task);
        if (task instanceof DateTimeTask) {
            DateTimeTask dt = (DateTimeTask) task;
            calendar.add(dt);
            storage.saveTask(task.getCode(), task.getStatus(), task.getDescription(), dt.getDateTime());
        } else {
            storage.saveTask(task.getCode(), task.getStatus(), task.getDescription());
        }
    }

    /**
     * Returns the String representation of all tasks in an array.
     *
     * @return String array containing a header as the first element and enumerated tasks for subsequent elements.
     */
    public String[] returnItems() {
        String[] itemList = new String[this.items.size() + 1];
        itemList[0] = "Here are the tasks in your list:";
        for (int i = 0; i < this.items.size(); i++) {
            itemList[i + 1] = (i + 1) + "." + this.items.get(i);
        }
        return itemList;
    }

    /**
     * Returns a formatted String representation of the number of tasks.
     *
     * @param offset Number to decrement current number of tasks by.
     * @return String array containing a header as the first element and enumerated tasks for subsequent elements.
     */
    public String returnItemCount(int offset) {
        return "Now you have " + (this.items.size() - offset) + " tasks in the list.";
    }

    /**
     * Changes the status of a task to done.
     *
     * @param index Index of task to be modified.
     * @param storage Storage object to save modification of task status.
     * @return Task that was modified.
     * @throws DukeException If index is invalid.
     */
    public Task markDone(int index, Storage storage) throws DukeException {
        if (index > this.items.size() || index < 1) {
            throw new DukeException(DukeException.Type.INDEX);
        }
        Task t = this.items.get(index - 1);
        t.markDone();
        storage.saveTaskDone(index);
        return t;
    }

    /**
     * Returns String representation of a task.
     *
     * @param index Index of task to be returned.
     * @return String representation of a task at index.
     */
    public String returnTask(int index) {
        return this.items.get(index - 1).toString();
    }

    /**
     * Returns String representation of the last task added.
     *
     * @return String representation of the last task added.
     */
    public String returnLastTask() {
        return this.returnTask(items.size());
    }

    /**
     * Removes a task completely.
     *
     * @param index Index of task to be removed.
     * @param storage Storage object to save removal of task.
     * @return Task that was removed.
     * @throws DukeException If index is invalid.
     */
    public Task removeTask(int index, Storage storage) throws DukeException {
        if (index > this.items.size() || index < 1) {
            throw new DukeException(DukeException.Type.INDEX);
        }
        storage.removeTask(index - 1);
        return items.remove(index - 1);
    }

    /**
     * Returns String array of formatted String representations of all events occurring before a given date and time.
     *
     * @param dt Date and time limit of events returned.
     * @return String array with each String representing an event.
     */
    public String[] getEventsAt(LocalDateTime dt) {
        ArrayList<DateTimeTask> tasks = calendar.getEventsAt(dt);
        String[] strArr = new String[tasks.size() + 1];
        strArr[0] = "Here are the Events happening before "
                + dt.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a")) + ":";
        int count = 1;
        for (DateTimeTask dtTask : tasks) {
            strArr[count++] = dtTask.toString();
        }
        return strArr;
    }

    /**
     * Returns String array of formatted String representations of all deadlines due by given date and time.
     *
     * @param dt Date and time limit of deadlines returned.
     * @return String array with each String representing deadline.
     */
    public String[] getDeadlinesBy(LocalDateTime dt) {
        ArrayList<DateTimeTask> tasks = calendar.getDeadlinesBy(dt);
        String[] strArr = new String[tasks.size() + 1];
        strArr[0] = "Here are the Deadlines to be completed by "
                + dt.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a")) + ":";
        int count = 1;
        for (DateTimeTask dtTask : tasks) {
            strArr[count++] = dtTask.toString();
        }
        return strArr;
    }

    /**
     * Returns String array of all events and deadlines formatted as Strings occurring before a given date and time.
     *
     * @param dt Date and time limit of events and deadlines returned.
     * @return String array with each String representing an event or deadline.
     */
    public String[] getAllBy(LocalDateTime dt) {
        ArrayList<DateTimeTask> tasks = calendar.getAllBy(dt);
        String[] strArr = new String[tasks.size() + 1];
        strArr[0] = "Here are the timed tasks occurring before "
                + dt.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a")) + ":";
        int count = 1;
        for (DateTimeTask dtTask : tasks) {
            strArr[count++] = dtTask.toString();
        }
        return strArr;
    }

    /**
     * Returns String array of all tasks formatted as Strings that completely or partially match the toFind argument.
     *
     * @param toFind Substring to be found in tasks.
     * @return String array with each String representing a task that matches toFind.
     */
    public String[] returnFoundItem(String toFind) {
        ArrayList<String > strArr = new ArrayList<>();
        strArr.add("Here are the matching tasks in your list:");
        int count = 1;
        for (Task t : items) {
            if (t.getDescription().contains(toFind)) {
                strArr.add(count++ + "." + t);
            }
        }
        return strArr.toArray(new String[strArr.size()]);
    }
}
