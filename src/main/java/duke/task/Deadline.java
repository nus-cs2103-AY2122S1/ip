package duke.task;

import java.time.LocalDate;
import duke.io.TextColor;

public class Deadline extends Task {
    private LocalDate date;

    public Deadline(String name, LocalDate date) {
        super(name);
        this.date = date;
    }

    /**
     * Returns whether the deadline is today.
     *
     * @return true if the date is today. False otherwise.
     */
    @Override
    public boolean isToday() {
        return date.equals(LocalDate.now());
    }

    /**
     * Loads a deadline from data parsed from the save file
     *
     * @param loadData A line from the csv, split by commas
     * @return Deadline created from provided data
     */
    public static Deadline load(String[] loadData) {
        boolean done = loadData[1].equals("o");
        String name = loadData[2];
        LocalDate time = LocalDate.parse(loadData[3]);

        Deadline deadline = new Deadline(name, time);
        if (done) {
            deadline.doTask();
        }

        return deadline;
    }

    /**
     * Returns whether the deadline has passed.
     *
     * @return true if the date is before today. False otherwise.
     */
    @Override
    public boolean isExpired() {
        return date.isBefore(LocalDate.now());
    }

    /**
     * Returns a string representation of the deadline and its data
     *
     * @return string representation of the deadline
     */
    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + date + ")";
    }

    /**
     * Returns a string representing the deadline compliant to the saveFile format
     *
     * @return String to be saved as a line in save.csv
     */
    @Override
    public String getSaveString() {
        return "d," + super.getSaveString() + "," + date;
    }
}
