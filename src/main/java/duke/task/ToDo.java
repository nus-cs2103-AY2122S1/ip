package duke.task;

/**
 * A class representing a ToDo task.
 */
public class ToDo extends Task {
    /**
     * Constructs a Todo object using then given task details.
     *
     * @param taskDetails The given task details.
     */
    public ToDo (String taskDetails) {
        super(taskDetails);
    }

    /**
     * Returns a letter as a String that represents the task type.
     *
     * @return The letter "T" that represents a ToDo.
     */
    public String getTaskType() {
        return "T";
    }

    public void setDateAndTime(String date, String time) {
        //Empty method. No date and time to set.
    }

    /**
     * Returns the String representation of the ToDo.
     *
     * @return String representation of the ToDo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
