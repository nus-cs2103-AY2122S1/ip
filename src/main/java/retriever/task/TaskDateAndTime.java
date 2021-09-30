package retriever.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class helps to manage the date and time formatting
 * for tasks.
 */
public class TaskDateAndTime {
    private String userInputDateAndTime;
    private LocalDate taskDateAndTime;

    /**
     * Initializes a date and time for a task.
     *
     * @param userInputDateAndTime The date and time entered by the user.
     */
    public TaskDateAndTime(String userInputDateAndTime) {
        this.userInputDateAndTime = userInputDateAndTime;
        parseDateAndTime();
    }

    /**
     * Parses and Stores the date in the desired format.
     */
    public void parseDateAndTime() {
        String[] userInputDate = userInputDateAndTime.split("/");

        if (userInputDate.length == 3) {
            taskDateAndTime = LocalDate.parse(userInputDate[2] + "-" + userInputDate[1] + "-" + userInputDate[0]);
        }
    }

    /**
     * Returns the string representation of date in a
     * specified format.
     *
     * @return Formatted date.
     */
    @Override
    public String toString() {
        return taskDateAndTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Returns a boolean, suggesting if the two dates are the same
     * or not.
     *
     * @param o The object that contains the date to be checked against.
     * @return A boolean, true, if both the dates are same.
     */
    @Override
    public boolean equals(Object o) {
        TaskDateAndTime date = (TaskDateAndTime) o;
        return taskDateAndTime.compareTo(date.taskDateAndTime) == 0;
    }
}
