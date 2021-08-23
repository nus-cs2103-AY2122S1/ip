import java.util.ArrayList;
import java.time.LocalDate;

public class TaskList {
    /** The content of the task */
    private String description;

    /** Boolean value storing whether the task is done */
    private Boolean isDone;

    /** The category of the task in Enum */
    private Duke.Category cat;

    private ArrayList<TaskList> arr = new ArrayList<>();

    private Parser parser;

    private boolean isPreExisting;


    /**
     * Constructor for various tasks in the TaskList
     * @param description The content of the task
     * @param cat The category of the task
     */
    public TaskList(String description, Duke.Category cat) {

        this.description = description;
        this.isDone = false;
        this.cat = cat;
        this.isPreExisting = false;
        parser = new Parser(description);
    }

    public TaskList() {

    }

    /**
     * Method to mark a task in the list as done
     */
    public void markAsDone(int i) {
        arr.get(i).isDone = true;
    }

    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Method to change the status of the task icon depending on
     * whether the task is done or not
     * @return The status icon of the task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void addTaskToList(TaskList task) {
        arr.add(task);
    }

    public TaskList getTaskFromList(int i) {
        return arr.get(i);
    }

    public int numberOfTasks() {
        return arr.size();
    }

    public void deleteTask(int i) {
        arr.remove(i);
    }

    public boolean getPreExisting() {
        return isPreExisting;
    }

    public void setPreExisting() {
      isPreExisting = true;
    }

    public String getDescription() {
       return "[" + this.description.charAt(1) + "]" + "[" + this.getStatusIcon() + "] " + this.description.split("\\s",3)[2];

    }


    /**
     * Method to custom print the task based on category
     * @return String comprising the type and content of the task
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
