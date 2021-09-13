package sariel.util.tasks;

import sariel.util.commons.Messages;

public class Event extends DatedTask {
    private static String label = "[E]";



    private Event(String name, String date) {
        super(name.trim(), date.trim());

    }

    /**
     * The factory method of the Event class that takes in the
     * String that has not been split.
     *
     * @param total The String with the /at delimiter
     * @return The Event from it.
     * @throws DukeException
     */
    public static Event of(String total) throws DukeException {
        String[] ss = total.split("/at", 2);
        enforceArguments(ss, Messages.EVENT_NO_INPUT_ERROR_MESSAGE);
        return new Event(ss[0], ss[1]);
    }

    /**
     * The factory method of the Event class that takes in the String that has been split.
     *
     * @param name The name of the object.
     * @param date The date of the object.
     * @return The Event object that is created.
     * @throws DukeException
     */
    public static Event of(String name, String date) throws DukeException {
        assert name != null : "Event of method name is null";
        assert date != null : "Event of method date is null";
        return new Event(name, date);
    }



    @Override
    public String toString() {
        return this.label + super.toString() + " (at: " + this.localDate() + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Event) {
            Event e = (Event) obj;
            return this.name.equals(e.name) && e.localDate.equals(this.localDate);
        }
        return false;
    }

    @Override
    public String encode() {
        //String indicating whether this task is done or not.
        String d = this.isDone()
                ? Task.DONE
                : Task.NOTDONE;

        return Task.Label.E + Task.DELIMITER + d + Task.DELIMITER + this.name + Task.DELIMITER + this.localDate;
    }
}
