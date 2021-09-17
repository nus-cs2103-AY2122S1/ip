/**
 * This class encapsulates the Deadline Task.
 *
 * @author Megan Wee Rui En
 * @version CS2103T AY21/22 Semester 1
 */

package duke.tasks;

import java.time.LocalDate;
import java.util.ArrayList;

public class Deadline extends Task {

    protected ArrayList<String> tags = new ArrayList<>();
    private final LocalDate dateBy;

    // This is used only for loading from the storage
    /**
     * Constructs a Deadline object.
     *
     * @param description Description of Deadline object
     * @param isDone whether the task has been completed
     * @param dateBy The date the deadline task is due by.
     */
    public Deadline(String description, LocalDate dateBy, boolean isDone, ArrayList<String> tags) {
        super(description);
        this.dateBy = dateBy;
        this.isDone = isDone;
        this.tags = tags;
    }

    /**
     * Constructs a Deadline object.
     *
     * @param description Description of Deadline object
     * @param dateBy The date the deadline task is due by.
     */
    public Deadline(String description, LocalDate dateBy) {
        super(description);
        this.dateBy = dateBy;
        this.isDone = false;
    }

    /**
     * Returns the string of the task to be represented in the list.
     *
     * @return the string of the task to be represented in the list
     */
    @Override
    public String toString() {
        return String.format("[D]%s%s (by: %d %s %d) %s", super.toString(), description, dateBy.getDayOfMonth(),
                dateBy.getMonth().toString(), dateBy.getYear(), getTags(this.tags));
//        return "[D]" + super.toString() + description + " (by: " + dateBy.getDayOfMonth()
//                + " " + dateBy.getMonth().toString() + " " + dateBy.getYear() + ")";
    }

    /**
     * Returns the string of the task to be represented in the text file.
     *
     * @return the string of the task to be represented in the text file
     */
    @Override
    public String getStatusString() {
        return String.format("D@%d@%s@%s@%s@", (isDone ? 1 : 0), this.description, this.dateBy.toString(),
                getTagsForStorage(this.tags));
    }
}
