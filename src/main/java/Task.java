import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Task {

    private boolean isDone;
    private final String taskDescription;

    /**
     * Constructor for a task.
     * @param taskDescription The name of the task.
     */
    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    /**
     * Updates the task status of completion as done.
     */
    public void completeTask() {
        this.isDone = true;
        System.out.println("Nice! I have marked this task as done: \n"
                + this);
    }

    /**
     * The output message when the task is deleted.
     */
    public void deleteMessage() {
        System.out.println("Noted. I've removed this task: \n"
                + this);
    }

    /**
     * Gets the task name.
     * @return Task name.
     */
    public String getTaskDescription() {
        return taskDescription;
    }

    /**
     * Converts the input string from 24-hour time to 12-hour time representation.
     * @param timeString The input string
     * @return The converted string.
     */
    private String convertTime(String timeString) {
        try {
            int timeIn24hRepresentation = Integer.parseInt(timeString);

            if (timeIn24hRepresentation < 0 || timeIn24hRepresentation > 2359
                    || Integer.parseInt(timeString.substring(2,3)) > 5) {
                return timeString;
            } else {
                int hours = timeIn24hRepresentation/100;
                int minutes = timeIn24hRepresentation%100;

                if (hours > 12) {
                    hours -= 12;
                    return hours + ":" + minutes + "PM";
                } else {
                    return hours + ":" + minutes + "AM";
                }
            }
        } catch (NumberFormatException e) {
            return timeString;
        }
    }

    /**
     * Converts the inputString into DD MMM YYYY format, or 12-hour time representation if applicable.
     * @param inputString The input string.
     * @return A string in DD MMM YYYY TIME format if inputString is in "YYYY-MM-DD TIME" format.
     */
    public String convert(String inputString) {
        if (inputString.length() < 10) {
            return convertTime(inputString);
        }

        String dateString = inputString.trim().substring(0, 10);
        String timeString = inputString.trim().substring(10).trim();
        try {
            String date = LocalDate.parse(dateString).format(DateTimeFormatter.ofPattern("d MMM yyyy"));

            if (timeString.length() == 4) {
                return date + " " + convertTime(timeString);
            }

            return date + timeString;
        } catch (DateTimeParseException e) {
            return inputString;
        }
    }

    /**
     * @return The string representation for the task.
     */
    @Override
    public String toString() {
        return "[" + (isDone ? "x" : " ") + "] " + taskDescription;
    }
}
