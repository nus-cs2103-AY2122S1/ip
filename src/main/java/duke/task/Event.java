package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents the "Event" task type. It encapsulates an Event's name, type and deadline
 */
public class Event extends Task {
    private String taskName;
    private String at;
    private String type = "E";
    private LocalDate date;
    private int time;
    private int day;
    private int month;
    private int year;

    /**
     * Constructor for an Event task
     *
     * @param taskName a short description of the task
     */
    public Event(String taskName) {
        String[] taskAtSplit = taskName.split("/", 2);

        if (taskName.contains("|")) {
            taskAtSplit = taskName.split("\\|", 2);
        } else if (taskName.contains("/at")) {
            taskAtSplit = taskName.split("/at", 2);
        }

        this.taskName = taskAtSplit[0].trim();
        this.at = taskAtSplit[1].trim();
        String[] dateTimeSplit = this.at.split(" ", 2);
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
     * Returns the full description (name + deadline) of the Event task
     *
     * @return full description of the Event task
     */
    public String showTask() {
        return this.taskName + " (at: "
                + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ", "
                + this.time + ")";
    }

    /**
     * Returns only the name of the Event task
     *
     * @return name of the Event task
     */
    public String showTaskOnly() {
        return this.taskName;
    }

    /**
     * Returns the deadline of the Event task
     *
     * @return deadline of the Event task
     */
    public String showWhen() {
        return this.at;
    }

    /**
     * Returns the type of the Event task
     *
     * @return task type "E"
     */
    public String showType() {
        return this.type;
    }

    /**
     * Returns the date of the Event task in "MMM d yyyy" format
     *
     * @return date of the Event task
     */
    public LocalDate showDate() {
        return this.date;
    }

    /**
     * Returns ths time of the Event task in 24-hour format
     *
     * @return time of the Event task
     */
    public int showTime() {
        return this.time;
    }
}
