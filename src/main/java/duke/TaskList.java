package duke;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The TaskList class stores the list of items. Items are stored in
 * an ArrayList of Strings. Supports adding of new items to the list
 * and printing of the entire current list.
 */
public class TaskList {
    /** The list of tasks stored */
    private ArrayList<Task> list;
    private Ui ui;

    /**
     * Constructor for TaskList class.
     */
    public TaskList() {
        this.list = new ArrayList<Task>();
        this.ui = new Ui();
    }

    /**
     * Constructor for TaskList.
     * Constructs the list based on the given array of strings.
     *
     * @param tasklist An array of strings of tasks.
     */
    public TaskList(ArrayList<String> tasklist) {
        this();
        for (String s : tasklist) {
            String[] splitData = s.split(",");
            switch(splitData[0]) {
            case("T"):
                addTask(splitData[2], Boolean.parseBoolean(splitData[1]));
                break;
            case("D"):
                addTask(splitData[2], LocalDate.parse(splitData[3]),"deadline", Boolean.parseBoolean(splitData[1]));
                break;
            case("E"):
                addTask(splitData[2], LocalDate.parse(splitData[3]), "event", Boolean.parseBoolean(splitData[1]));
            }
        }
    }

    /**
     * Returns the array of tasks.
     *
     * @return Array of tasks.
     */
    public ArrayList<Task> getList() {
        return this.list;
    }

    /**
     * Adds a task the list and saves to the file.
     *
     * @param description The description of the task.
     */
    public String addTask(String description) {
        Task task = new ToDo(description);
        this.list.add(task);
        ui.writeOutput("Task added successfully: \n" + task + "\nNumber of tasks in list: " + list.size());
        return task.saveString();
    }

    /**
     * Adds a task the list without saving to file.
     *
     * @param description The description of the task.
     * @param isDone Indicates if the task is done.
     */
    public void addTask(String description, boolean isDone) {
        Task task = new ToDo(description, isDone);
        this.list.add(task);
    }

    /**
     * Adds a task the list and saves to the file.
     * Task added have a date/time attached.
     *
     * @param description The description of the task.
     * @param date The date/time attached to the task.
     * @param type Indicates if the task is a Deadline or Event.
     */
    public String addTask(String description, LocalDate date, String type) {
        Task task;
        if (type.equals("deadline")) {
            task = new Deadline(description, date);
        } else {
            task = new Event(description, date);
        }
        this.list.add(task);
        ui.writeOutput("Task added successfully: \n" + task + "\nNumber of tasks in list: " + list.size());
        return task.saveString();
    }

    /**
     * Adds a task the list without saving to file.
     * Task added have a date/time attached.
     *
     * @param description The description of the task.
     * @param dateTime The date associated with the task.
     * @param type The type of task.
     * @param isDone Indicates if the task is done.
     */
    public void addTask(String description, LocalDate dateTime, String type, boolean isDone) {
        Task task;
        if (type.equals("deadline")) {
            task = new Deadline(description, dateTime, isDone);
        } else {
            task = new Event(description, dateTime, isDone);
        }
        this.list.add(task);
    }

    /**
     * Changes the status for the task indicated by the user.
     *
     * @param taskNumber The index(plus 1) of the task to be marked as done.
     * @return The string representation of the task after it is marked as done.
     */
    public String changeTaskStatus(int taskNumber) throws DukeException {
        if (taskNumber <= 0 || taskNumber > this.list.size()) {
            throw new DukeException("Task does not exist. Use list to check all tasks available.");
        } else {
            Task task = list.get(taskNumber - 1);
            task.setDone(true);
            return task.toString() + "\nNumber of tasks remaining: " + list.size();
        }
    }

    /**
     * Deletes the task indicated from the list.
     *
     * @param taskNumber The index of the task to be deleted.
     * @return String representation of the task deleted.
     * @throws DukeException If taskNumber is an invalid index.
     */
    public String deleteTask(int taskNumber) throws DukeException {
        if (taskNumber <= 0 || taskNumber > this.list.size()) {
            throw new DukeException("Task does not exist. Use list to check all tasks available.");
        } else {
            Task task = list.get(taskNumber - 1);
            list.remove(taskNumber - 1);
            return task.toString() + "\nNumber of tasks remaining: " + list.size();
        }
    }

    public ArrayList<String> findTask(String keyword) {
        ArrayList<String> resultArray = new ArrayList<>();
        keyword = keyword.toLowerCase();
        for (Task t : list) {
            if (t.getDescription().toLowerCase().contains(keyword)) {
                resultArray.add(t.toString());
            }
        }
        return resultArray;
    }

    /**
     * Returns an array of tasks in their string representation.
     *
     * @return Array of tasks in string form.
     */
    public ArrayList<String> getTaskList() {
        ArrayList<String> tasks = new ArrayList<>();
        for (Task t : list) {
            tasks.add(t.toString());
        }
        return tasks;
    }
}
