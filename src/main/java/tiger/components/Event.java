package tiger.components;

import tiger.exceptions.inputs.TigerEmptyStringException;
import tiger.exceptions.storage.TigerStorageLoadException;

public class Event extends Task {

    private String eventAt;

    private Event(String taskDescription, boolean done, String eventAt) {
        super(taskDescription, done);
        this.eventAt = eventAt;
    }

    public static Event of(String taskDescription, boolean done, String eventAt) throws TigerEmptyStringException {
        return new Event(taskDescription, done, eventAt);
    }

    @Override
    public Event markDone() {
        return new Event(this.taskDescription, true, this.eventAt);
    }

    @Override
    public String toString() {
        if (this.done) {
            return String.format("[E] [X] %s (at %s)", this.taskDescription, this.eventAt);
        } else {
            return String.format("[E] [ ] %s (at %s)", this.taskDescription, this.eventAt);
        }
    }

    protected String getStorageRepresentation() {
        return String.format("E;%s;%s;%s", this.done, this.taskDescription, this.eventAt);
    }

    protected static Event getTaskFromStringRepresentation(String s) throws TigerStorageLoadException {
        /* s should be of the form T|true/false|taskDescription| */
        String[] stringArray = s.split(";");
        int length = stringArray.length;
        try {
            assert (length == 4);
            // check if task is indeed a Event task
            assert (stringArray[0].equals("E"));
            // check task done value is either true or false
            assert (stringArray[1].equals("true") || stringArray[1].equals("false"));
            // check that task description is non-empty
            assert (!stringArray[2].equals(""));
            // check that the event timing is non-empty
            assert (!stringArray[3].equals(""));
            if (stringArray[1].equals("true")) {
                return new Event(stringArray[2], true, stringArray[3]); // task description, done, timing
            } else {
                return new Event(stringArray[2], false, stringArray[3]); // task description, done, timing
            }
        } catch (AssertionError e) {
            throw new TigerStorageLoadException(e.toString());
        }
    }
}
