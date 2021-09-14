package duke;

/**
 * This class simulates a special Task with a designated time
 * and date, using the word 'at'.
 */
public class Event extends Task {

    /**
     * Constructor of an Event.
     *
     * @param description Description of the event.
     */
    public Event(String description) {
        super(description);
    }

    /**
     * Returns the description of the current Event.
     *
     * @return Description of the current Event.
     */
    @Override
    public String getDescription() {
        String temp = super.getDescription();
        if (temp.endsWith(")")) {
            return temp;
        }

        if (temp.startsWith("e ")) {
            temp = temp.replace("e ", "");
        } else {
            temp = temp.replace("event ", "");
        }

        // Obtain the time and date of the Event
        String oldDate = temp.substring(temp.lastIndexOf("at") + 3);


        DateTimeConverter converter = new DateTimeConverter();
        String newDate = converter.convertDateAndTime(oldDate);
        temp = temp.replace(oldDate, newDate);

        // Reformat if user types a character before "at" like /at
        int intBeforeBy = temp.lastIndexOf("at") - 1;
        if (temp.charAt(intBeforeBy) != ' ') {
            char tempChar = temp.charAt(intBeforeBy);
            temp = temp.replace(String.valueOf(tempChar), "(");
            return temp + ")";
        }

        String afterDescription = temp.substring(temp.indexOf("at"));
        String description = temp.substring(0, temp.indexOf("at"));
        return description + "(" + afterDescription + ")";
    }

    /**
     * Marks the current Event as done.
     */
    @Override
    public void markDone() {
        super.markDone();
    }

    /**
     * Returns the string representation of the
     * completion status of the Event.
     *
     * @return String representation of the
     *         completion status of the Event.
     */
    @Override
    public String getStatusIcon() {
        return "[E]" + super.getStatusIcon();
    }

    /**
     * Returns the string representation for the Event's description
     * and completion status.
     *
     * @return String representation for the Event's description
     *         and completion status.
     */
    @Override
    public String printTask() {
        return this.getStatusIcon() + " " + this.getDescription();
    }
}
