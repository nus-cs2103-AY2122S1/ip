package duke.tasks;

public class DoAfter extends Task {
    private String prevTaskDescription;

    public DoAfter(String description, String prevTaskDescription) {
        super(description);
        this.prevTaskDescription = prevTaskDescription;
    }

    public DoAfter(String done, String description, String prevTaskDescription) {
        super(description);
        this.prevTaskDescription = prevTaskDescription;
        if ((done.equals("X"))) {
            this.setIsDone(true);
        } else {
            this.setIsDone(false);
        }
    }

    public String getPrevTaskDescription() {
        return prevTaskDescription;
    }

    public String setPrevTaskDescription(String prevTaskDescription) {
        this.prevTaskDescription = prevTaskDescription;
        return this.prevTaskDescription;
    }

    /**
     * Returns formatted String representation of a DoAfter task to facilitate ease of reading.
     *
     * @return String object to represent DoAfter task in a more readable manner.
     */
    @Override
    public String formatString() {
        return toString();
    }

    /**
     * Returns String representation of DoAfter task.
     *
     * @return String type object that includes the task type, parent
     * toString method(), and start and end time.
     */
    @Override
    public String toString() {
        return "[A]" + super.toString() + " (to be done after: " + this.prevTaskDescription + ")";
    }
}
