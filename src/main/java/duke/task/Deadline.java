package duke.task;

import java.time.LocalDate;

/**
 * Class representing a deadline that has a date to be completed by.
 */
public class Deadline extends Task {
    private LocalDate date;

    /**
     * Constructor for deadline, setting its name and date, and marking it not done.
     *
     * @param name Name of the deadline.
     * @param date Date the deadline is by.
     */
    public Deadline(String name, LocalDate date) {
        super(name);
        this.date = date;
    }

    /**
     * Loads a deadline from data parsed from the save file.
     *
     * @param loadDatas A line from the csv, split by commas.
     * @return Deadline created from provided data.
     */
    public static Deadline load(String[] loadDatas) {
        boolean isDone = loadDatas[1].equals("o");
        String name = loadDatas[2];
        LocalDate time = LocalDate.parse(loadDatas[3]);

        Deadline deadline = new Deadline(name, time);
        if (isDone) {
            deadline.doTask();
        }

        return deadline;
    }

    /**
     * Returns whether the deadline has passed.
     *
     * @return True if the date is before today. False otherwise.
     */
    @Override
    public boolean isExpired() {
        return date.isBefore(LocalDate.now());
    }

    /**
     * Returns a string representation of the deadline and its data.
     *
     * @return String representation of the deadline.
     */
    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + date + ")";
    }

    /**
     * Returns a string representing the deadline compliant to the saveFile format.
     *
     * @return String to be saved as a line in save.csv.
     */
    @Override
    public String getSaveString() {
        return "d," + super.getSaveString() + "," + date;
    }
}
