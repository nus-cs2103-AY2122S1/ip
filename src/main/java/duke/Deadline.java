package duke;

/**
 * This class simulates a special type of Task which has a date and time
 * for which it is to be completed by, using the word 'by'.
 */
public class Deadline extends Task {
    /**
     * Constructor for Deadline.
     *
     * @param description Description of the Deadline object.
     */
    public Deadline(String description) {
        super(description);
    }

    /**
     * Returns the description of the current deadline.
     *
     * @return Description of the current deadline task.
     */
    @Override
    public String getDescription() {
        String temp = super.getDescription();
        if (temp.endsWith(")")) {
            return temp;
        }

        if (temp.startsWith("d ")) {
            temp = temp.replace("d", "");
        } else {
            temp = temp.replace("deadline ", "");
        }

        // Obtain the time and date of the deadline
        String oldDate = temp.substring(temp.lastIndexOf("by") + 3);
        DateTimeConverter converter = new DateTimeConverter();

        String newDate = converter.convertDateAndTime(oldDate);
        temp = temp.replace(oldDate, newDate);

        int intBeforeBy = temp.lastIndexOf("by") - 1;

        // Reformat if user types a character before "by" like /by
        if (temp.charAt(intBeforeBy) != ' ') {
            char tempChar = temp.charAt(intBeforeBy);
            temp = temp.replace(String.valueOf(tempChar), "(");
            return temp + ")";
        }

        String afterDescription = temp.substring(temp.indexOf("by"));
        String description = temp.substring(0, temp.indexOf("by"));
        return description + "(" + afterDescription + ")";
    }

    /**
     * Marks the current Deadline as done.
     */
    @Override
    public void markDone() {
        super.markDone();
    }

    /**
     * Returns the current status icon of the current Deadline.
     *
     * @return Current status icon of the deadline.
     */
    @Override
    public String getStatusIcon() {
        return "[D]" + super.getStatusIcon();
    }

    /**
     * Return a string containing the Deadline description
     * and its completion status.
     *
     * @return String containing the Deadline description
     *         and its completion status.
     */
    @Override
    public String printTask() {
        return this.getStatusIcon() + " " + this.getDescription();
    }
}
