package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private String description;
    private String time;

    /**
     * A constructor for the Event task.
     *
     * @param description user input task description.
     * @param isDone status of the task.
     */
    public Event(String description, Boolean isDone) {
        super(description, isDone, "E");
        this.description = description;
    }

    /**
     * Returns the status and description of the event task.
     *
     * @return a string representation of the event task.
     */
    public String getTask() {
        // split text and duration
        String[] splitted = description.split("/from ", 2);
        String text = splitted[0].trim();
        String duration = splitted[1].trim();

        // split start date and end date
        String[] splitted2 = duration.split("/to ", 2);
        String startDate = splitted2[0].trim();
        String endDate = splitted2[1].trim();

        LocalDateTime startDateTime = LocalDateTime.parse(startDate);
        LocalDateTime endDateTime = LocalDateTime.parse(endDate);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy HH:mm a");
        return "[E]" + "[" + super.getStatusIcon() + "] " + text + " (from: " + startDateTime.format(formatter) +
                " to " + endDateTime.format(formatter) + ")";
    }
}
