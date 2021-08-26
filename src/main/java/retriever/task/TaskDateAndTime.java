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
        isValidDate();
    }

    /**
     * Validates if the date is entered in the correct
     * format.
     *
     * @return A boolean indicating if the date is valid.
     */
    public boolean isValidDate() {
        String[] userInputDate = userInputDateAndTime.split("/");

        if(userInputDate.length == 3) {
            taskDateAndTime = LocalDate.parse(userInputDate[2] + "-" +userInputDate[1] + "-" + userInputDate[0]);
        }

        return userInputDate.length == 3;
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
}
