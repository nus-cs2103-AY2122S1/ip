package tiger.components;

import tiger.exceptions.TigerStorageLoadException;

public class DeadLine extends Task {

    private String deadLine;

    private DeadLine(String taskDescription, boolean done, String deadLine) {
        super(taskDescription, done);
        this.deadLine = deadLine;
    }

    public static DeadLine of(String taskDescription, boolean done, String deadLine) {
        return new DeadLine(taskDescription, done, deadLine);
    }

    @Override
    public DeadLine markDone() {
        return new DeadLine(this.taskDescription, true, this.deadLine);
    }

    @Override
    public String toString() {
        if (this.done) {
            return String.format("[D] [X] %s (by %s)", this.taskDescription, this.deadLine);
        } else {
            return String.format("[D] [ ] %s (by %s)", this.taskDescription, this.deadLine);
        }
    }

    protected String getStorageRepresentation() {
        return String.format("D;%s;%s;%s", this.done, this.taskDescription, this.deadLine);
    }

    protected static DeadLine getTaskFromStringRepresentation(String s) throws TigerStorageLoadException {
        /* s should be of the form T|true/false|taskDescription| */
        String[] stringArray = s.split(";", 4);
        int length = stringArray.length;
        try {
            assert (length == 4);
            // check if task is indeed a Deadline task
            assert (stringArray[0].equals("D"));
            // check task done value is either true or false
            assert (stringArray[1].equals("true") || stringArray[1].equals("false"));
            // check that task description is non-empty
            assert (!stringArray[2].equals(""));
            // check that the event timing is non-empty
            assert (!stringArray[3].equals(""));
            if (stringArray[1].equals("true")) {
                return new DeadLine(stringArray[2], true, stringArray[3]); // task description, done, timing
            } else {
                return new DeadLine(stringArray[2], false, stringArray[3]); // task description, done, timing
            }
        } catch (AssertionError e) {
            throw new TigerStorageLoadException(e.toString());
        }
    }


}
