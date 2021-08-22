/**
 * The general task class
 *
 * @Author Houten Teo
 * @version CS2103T week 2
 */

public class Task {

    private String taskName;
    private boolean isDone;

    /**
     * Constructor for the Task class
     * @param taskName Name of the task.
     * @param isDone True if the task is completed and false otherwise.
     */
    public Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    /**
     * Method to mark the task as complete.
     * Additionally, prints out the taskName to allow user to validate.
     */
    public void markComplete() {
        if (isDone) {
            System.out.println("`" + taskName + "`" + " is already completed.");
        } else {
            this.isDone = true;
            System.out.println(
                    "Finally! Took you long enough to complete:" + taskName
            );
        }
    }

    /**
     * Method to return the corresponding status icon depending on
     * whether the task has been completed or not.
     * @return The String representation of the icon.
     */
    public String getStatusIcon() {
        if (isDone) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    /**
     * Method to return the corresponding type icon depending on
     * the type of the task.
     * @return
     */
    public String getTypeIcon() {
        return "[ ]";
    }

    public String createData() {
        return "";
    }

    public static Task getTaskFromString(String s) {
        String taskType = s.substring(0, 3);
        String taskDescription = s.substring(7);
        Task t = null;
        try {
            if (s.substring(3,6). equals("[X]")) {
                switch (taskType) {
                case "[T]":
                    t = new Todo(taskDescription, true);
                    break;
                case "[D]":
                    t =  new Deadline(taskDescription, true);
                    break;
                case "[E]":
                    t =  new Event(taskDescription, true);
                    break;
                }
            } else {
                switch (taskType) {
                case "[T]":
                    t = new Todo(taskDescription, false);
                    break;
                case "[D]":
                    t = new Deadline(taskDescription, false);
                    break;
                case "[E]":
                    t = new Event(taskDescription, false);
                    break;
                }
            }
        } catch (WrongCommandFormatException e) {
            System.out.println(e.getMessage());
            System.out.println("Error loading tasks");
        }
        return t;
    }
}
