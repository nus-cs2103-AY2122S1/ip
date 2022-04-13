package chadbot.data;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

import chadbot.ChadException;
import chadbot.task.DateTimeTask;
import chadbot.task.Deadline;
import chadbot.task.Event;
import chadbot.task.Task;
import chadbot.task.Todo;

public class TaskList {

    /** ArrayList to store all tasks */
    private ArrayList<Task> items;
    /** Calendar object to store dates and times of timed tasks */
    private Calendar calendar;
    private enum TaskType {
        T, E, D
    }

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
     * @throws ChadException If data is corrupt or missing information.
     * @throws FileNotFoundException If data file cannot be found.
     */
    public TaskList(ArrayList<String[]> taskArrayList) throws ChadException, FileNotFoundException {
        this.items = new ArrayList<>();
        this.calendar = new Calendar();
        for (String[] taskElements : taskArrayList) {
            Task task = convertSavedDataToTask(taskElements);
            if (task != null) {
                items.add(task);
            }
        }
    }


    private Task convertSavedDataToTask(String[] taskElements) throws ChadException {
        TaskType taskType = TaskType.valueOf(taskElements[0]);
        Task task;
        switch (taskType) {
        case T:
            String description = taskElements[2];
            task = new Todo(description);
            break;
        case E:
            String[] eventParameters = Arrays.copyOfRange(taskElements, 2, taskElements.length);
            task = new Event(eventParameters);
            calendar.add((DateTimeTask) task);
            break;
        case D:
            String[] deadlineParameters = Arrays.copyOfRange(taskElements, 2, taskElements.length);
            task = new Deadline(deadlineParameters);
            calendar.add((DateTimeTask) task);
            break;
        default:
            task = null;
            break;
        }
        boolean isTaskDone = taskElements[1].equals("X");
        if (task != null && isTaskDone) {
            task.markDone();
        }
        return task;
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
            storage.saveTimedTask(task.getCode(), task.getStatus(), task.getDescription(), dt.getDateTime());
        } else {
            storage.saveUntimedTask(task.getCode(), task.getStatus(), task.getDescription());
        }
    }

    /**
     * Returns the String representation of all tasks in an array.
     *
     * @return String array containing a header as the first element and enumerated tasks for subsequent elements.
     */
    public String[] returnItems() {
        int returnArraySize = this.items.size() + 1;
        String[] itemList = new String[returnArraySize];
        itemList[0] = "Here are the tasks in your list:";
        for (int i = 0; i < returnArraySize - 1; i++) {
            int indexNum = i + 1;
            Task currentTask = this.items.get(i);
            itemList[indexNum] = indexNum + "." + currentTask;
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
        int numberOfItems = this.items.size() - offset;
        return "Now you have " + numberOfItems + " tasks in the list.";
    }

    /**
     * Changes the status of a task to done.
     *
     * @param index Index of task to be modified.
     * @param storage Storage object to save modification of task status.
     * @return Task that was modified.
     * @throws ChadException If index is invalid.
     */
    public Task markDone(int index, Storage storage) throws ChadException {
        if (!isValidIndex(index)) {
            throw new ChadException(ChadException.Type.INDEX);
        }
        int storedIndex = index - 1;
        Task task = this.items.get(storedIndex);
        task.markDone();
        storage.saveTaskDone(index);
        return task;
    }

    private boolean isValidIndex(int index) {
        boolean isWithinSize = index <= this.items.size();
        boolean isValidIndex = index >= 1;
        if (isValidIndex && isWithinSize) {
            return true;
        }
        return false;
    }

    /**
     * Returns String representation of a task.
     *
     * @param index Index of task to be returned.
     * @return String representation of a task at index.
     */
    public String returnTask(int index) {
        assert items.size() >= index : "Index should be in range";
        int storedIndex = index - 1;
        return this.items.get(storedIndex).toString();
    }

    /**
     * Returns String representation of the last task added.
     *
     * @return String representation of the last task added.
     */
    public String returnLastTask() {
        int lastTaskIndex = items.size();
        return this.returnTask(lastTaskIndex);
    }

    /**
     * Removes a task completely.
     *
     * @param index Index of task to be removed.
     * @param storage Storage object to save removal of task.
     * @return Task that was removed.
     * @throws ChadException If index is invalid.
     */
    public Task removeTask(int index, Storage storage) throws ChadException {
        if (!isValidIndex(index)) {
            throw new ChadException(ChadException.Type.INDEX);
        }
        int storedIndex = index - 1;
        storage.removeTask(storedIndex);
        return items.remove(storedIndex);
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
