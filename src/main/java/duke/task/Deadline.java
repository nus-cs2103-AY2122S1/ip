package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents the "Deadline" task type. It encapsulates the Deadline's name, type and deadline.
 */
public class Deadline extends Task {
    private String taskDescription;
    private String by;
    private String type = "D";
    private LocalDate date;
    private int time;
    private int day;
    private int month;
    private int year;

    /**
     * Constructor for a Deadline task.
     *
     * @param taskDescription a short description of the task
     */
    public Deadline(String taskDescription) {
        assert taskDescription != null : "task description should not be null";

        String[] splitDescBy = taskDescription.split("/", 2);

        if (taskDescription.contains("|")) {
            splitDescBy = taskDescription.split("\\|", 2);
        } else if (taskDescription.contains("/by")) {
            splitDescBy = taskDescription.split("/by", 2);
        }

        this.taskDescription = splitDescBy[0].trim();
        this.by = splitDescBy[1].trim();
        String[] dateTimeSplit = this.by.split(" ", 2);
        this.time = Integer.parseInt(dateTimeSplit[1]);

        if (dateTimeSplit[0].contains("/")) {
            String[] dateSplit = dateTimeSplit[0].split("/", 3);
            this.day = Integer.parseInt(dateSplit[0]);
            this.month = Integer.parseInt(dateSplit[1]);
            this.year = Integer.parseInt(dateSplit[2]);
            this.date = LocalDate.of(this.year, this.month, this.day);
        } else if (dateTimeSplit[0].contains("-")) {
            this.date = LocalDate.parse(dateTimeSplit[0]);
        }
    }

    /**
     * Returns the full description (name + deadline) of the Deadline task
     *
     * @return full description of the Deadline task
     */
    public String showFullDescription() {
        return this.taskDescription + " (by: "
                + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ", "
                + this.time + ")";
    }

    /**
     * Returns only the name of the Deadline task
     *
     * @return name of the Deadline task
     */
    public String showTaskName() {
        return this.taskDescription;
    }

    /**
     * Returns the deadline of the Deadline task
     *
     * @return deadline of the Deadline task
     */
    public String showWhen() {
        return this.by;
    }

    /**
     * Returns the type of the Deadline task
     *
     * @return task type "D"
     */
    public String showType() {
        return this.type;
    }

    /**
     * Returns the date of the Deadline task in "MMM d yyyy" format
     *
     * @return date of the Deadline task
     */
    public LocalDate showDate() {
        return this.date;
    }

    /**
     * Returns ths time of the Deadline task in 24-hour format
     *
     * @return time of the Deadline task
     */
    public int showTime() {
        return this.time;
    }

}
