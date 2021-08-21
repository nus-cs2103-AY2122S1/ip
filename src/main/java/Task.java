import java.time.LocalDate;

/**
 * The Task class that encapsulates a directive given
 * by the user to track a certain activity and may have
 * start/end timings.
 */
public abstract class Task {

    //The description of the task
    protected String description;
    //Boolean of whether a task is done
    protected boolean isDone;
    protected LocalDate localDate;

    public abstract String strForSaving();

    /**
     * The constructor for the Task class
     * @param description The description of the task
     */
    public Task(String description, boolean isDone, LocalDate localDate) {
        this.description = firstLetter(description);
        this.localDate = localDate;
        this.isDone = isDone;
    }

    /**
     * The constructor for the Task class
     * @param description The description of the task
     */
    public Task(String description, boolean isDone) {
        this.description = firstLetter(description);
        this.isDone = isDone;
    }

    /**
     * Method that returns whether a task is done in string
     * @return "X" if done, " " if not
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Method to set a task as done
     */
    public void taskDone() {
        System.out.println(Responses.LINE + "\nYou have completed the task: " + "'"
                                          + this.description + "'!"
                                          + "\nI am so happy for you!\n"
                                          + Responses.LINE);
        this.isDone = true;
    }

    /**
     * Method to capitalize the first letter of the string
     * @param str The string
     * @return String but with first letter capitalized
     */
    public String firstLetter(String str) {
        str = str.substring(0, 1).toUpperCase() + str.substring(1);
        return str;
    }

    /**
     * Overridden toString method for the Task class
     * @return String representation of the Task object
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

}
