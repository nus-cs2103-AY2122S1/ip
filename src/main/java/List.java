import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;

import java.util.ArrayList;

/**
 * The List class stores the list of items. Items are stored in
 * an ArrayList of Strings. Supports adding of new items to the list
 * and printing of the entire current list.
 */
public class List {
    private ArrayList<Task> list;

    /**
     * Constructor for List class.
     */
    public List() {
        this.list = new ArrayList<Task>();
    }

    /**
     * Adds a task the list and saves to the file.
     *
     * @param description The description of the task
     */
    public void addTask(String description) {
        Task task = new ToDo(description);
        this.list.add(task);
        System.out.println("Task added successfully: \n" + task);
        System.out.println("Number of tasks in list: " + list.size());
        saveTask(task.saveString());
    }

    /**
     * Adds a task the list without saving to file.
     *
     * @param description The description of the task
     * @param isDone Indicates if the task is done
     */
    public void addTask(String description, boolean isDone) {
        Task task = new ToDo(description, isDone);
        this.list.add(task);
    }

    /**
     * Adds a task the list and saves to the file.
     * Task added have a date/time attached.
     *
     * @param description The description of the task
     * @param date The date/time attached to the task
     * @param type Indicates if the task is a Deadline or Event
     */
    public void addTask(String description, LocalDate date, String type) {
        Task task;
        if (type.equals("deadline")) {
            task = new Deadline(description, date);
            saveTask(task.saveString());
        } else {
            task = new Event(description, date);
            saveTask(task.saveString());
        }
        this.list.add(task);
        System.out.println("Task added successfully: \n" + task);
        System.out.println("Number of tasks in list: " + list.size());
    }

    /**
     * Adds a task the list without saving to file.
     * Task added have a date/time attached.
     *
     * @param description The description of the task
     * @param dateTime
     * @param type
     * @param isDone Indicates if the task is done
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
     * @param taskNumber The index(plus 1) of the task to be marked as done
     * @return The string representation of the task after it is marked as done
     */
    public String changeTaskStatus(int taskNumber) throws DukeException{
        if (taskNumber <= 0 || taskNumber > this.list.size()) {
            throw new DukeException("Task does not exist. Use list to check all tasks available.");
        } else {
            Task task = list.get(taskNumber - 1);
            task.setDone(true);
            rewriteFile();
            return task.toString() + "\nNumber of tasks remaining: " + list.size();
        }
    }

    /**
     * Deletes the task indicated from the list.
     *
     * @param taskNumber The index of the task to be deleted
     * @return String representation of the task deleted
     * @throws DukeException If taskNumber is an invalid index
     */
    public String deleteTask(int taskNumber) throws DukeException{
        if (taskNumber <= 0 || taskNumber > this.list.size()) {
            throw new DukeException("Task does not exist. Use list to check all tasks available.");
        } else {
            Task task = list.get(taskNumber - 1);
            list.remove(taskNumber - 1);
            rewriteFile();
            return task.toString() + "\nNumber of tasks remaining: " + list.size();
        }
    }

    /**
     * List the items in the list in the order added, along with a counter.
     */
    public void listItems() {
        for (int i = 1; i <= list.size(); i++) {
            System.out.println(i + ". " + list.get(i-1));
        }
    }

    /**
     * Save the given task string to file.
     *
     * @param taskString The task in string representation
     */
    private void saveTask(String taskString) {
        try {
            FileWriter writer = new FileWriter(Duke.filePath.toString(), true);
            writer.write(taskString + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving task.\n" + e.getMessage());
        }
    }

    /**
     * Rewrite the file with the latest list of tasks.
     */
    private void rewriteFile() {
        try {
            FileWriter writer = new FileWriter(Duke.filePath.toString());
            for (Task task : list) {
                writer.write(task.saveString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Failed to rewrite file\n" + e.getMessage());
        }
    }
}
