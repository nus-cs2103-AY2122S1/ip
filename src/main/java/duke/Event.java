package duke;

/**
 * This class simulates a special Task with a designated time
 * and date, using the word 'at'.
 */
public class Event extends Task {

    /**
     * Constructor of an Event.
     *
     * @param description description of the event.
     */
    public Event(String description) {
        super(description);
    }

    /**
     * Returns the description of the current Event
     *
     * @return the description of the current Event
     */
    @Override
    public String getDescription() {
        String temp = super.getDescription();
        if (temp.endsWith(")")) {
            return temp;
        }
        temp = temp.replace("event ", "");

        String oldDate = temp.substring(temp.lastIndexOf("at") + 3);
        DateTimeConverter converter = new DateTimeConverter();
        String newDate = converter.convertDateAndTime(oldDate);
        temp = temp.replace(oldDate, newDate);

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
    public void markedDone() {
        super.markedDone();
    }

    /**
     * Returns the string representation of the
     * completion status of the Event.
     *
     * @return the string representation of the
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
     * @return the string representation for the Event's description
     *         and completion status.
     */
    @Override
    public String printTask() {
        return this.getStatusIcon() + " " + this.getDescription();
    }
}
