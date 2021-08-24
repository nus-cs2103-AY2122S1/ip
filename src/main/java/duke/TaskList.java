package duke;

import java.util.ArrayList;

/**
 * Represents the tasks in the list.
 */
public class TaskList {

    /** The content of the task */
    private String description;

    /** Boolean value storing whether the task is done */
    private Boolean isDone;

    /** The category of the task in Enum */
    private Duke.Category cat;

    /** The array list representing the tasks */
    private ArrayList<TaskList> arr = new ArrayList<>();

    /** The parser class object */
    private Parser parser;

    /** Boolean value storing whether the task already exists in the hard disk */
    private boolean isPreExisting;

    /**
     * Initialises variables of the task.
     * @param description The content of the task.
     * @param cat The category of the task.
     */
    public TaskList(String description, Duke.Category cat) {
        this.description = description;
        this.isDone = false;
        this.cat = cat;
        this.isPreExisting = false;
        parser = new Parser(description);
    }

    /**
     * Default constructor
     */
    public TaskList() {

    }

    /**
     * Marks a task in the list as done.
     */
    public void markAsDone(int i) {
        arr.get(i).isDone = true;
    }

    /**
     * Returns the isDone field of the task.
     * @return boolean value representing whether the task is done.
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Changes the status of the task icon depending on
     * whether the task is done or not.
     * @return The status icon of the task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Adds task to the task arraylist.
     * @param task Task to be added.
     */
    public void addTaskToList(TaskList task) {
        arr.add(task);
    }

    /**
     * Returns a specific indexed task from the task arraylist.
     * @param i Index of the task to be returned.
     * @return Task with index i.
     */
    public TaskList getTaskFromList(int i) {
        return arr.get(i);
    }

    /**
     * Returns number of tasks in the list.
     * @return number of tasks in the task arraylist.
     */
    public int numberOfTasks() {
        return arr.size();
    }

    /**
     * Deletes task from the list.
     * @param i Index of the task to be deleted.
     */
    public void deleteTask(int i) {
        arr.remove(i);
    }

    /**
     * Returns whether the task already exists in the hard disk.
     * @return boolean value of the isPreExisting field of the task.
     */
    public boolean getPreExisting() {
        return isPreExisting;
    }

    /**
     * Sets isPreExisting to true.
     */
    public void setPreExisting() {
      isPreExisting = true;
    }

    /**
     * Returns the description of the task.
     * @return
     */
    public String getDescription() {
       return "[" + this.description.charAt(1) + "]" + "[" + this.getStatusIcon() + "] " + this.description.split("\\s",3)[2];

    }


    /**
     * Custom prints the task based on category.
     * @return String comprising the type and content of the task.
     */
    public String toString() {
        if (this.cat == Duke.Category.TODO) {
            return "[T]" + "[" + this.getStatusIcon() + "] " + parser.parseTask();
        } else if (this.cat == Duke.Category.DEADLINE) {
            return "[D]" + "[" + this.getStatusIcon() + "] " + parser.parseTask() + " (by: " + parser.parseTime() + ")";
        } else {
            return "[E]" + "[" + this.getStatusIcon() + "] " + parser.parseTask() + " (at: " + parser.parseTime() + ")";
        }
    }
}
