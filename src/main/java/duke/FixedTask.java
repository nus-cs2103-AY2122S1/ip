package duke;

/**
 * This class extends Task.
 */
public class FixedTask extends Task {
    protected String fixedDuration;

    /**
     * Constructor for Fixed Task. This handles user input, which comes in
     * the form of "fixed [DESCRIPTION] /for [FIXED DURATION]".
     *
     * @param input Raw input from the user.
     */
    public FixedTask(String input) {
        super(input.split(" /")[0].substring(6));
        this.fixedDuration = input.split(" /for ")[1];
    }

    /**
     * Constructor for Fixed Task. This is used when reading duke.txt file.
     *
     * @param description Description of the task activity.
     * @param fixedDuration Fixed duration for the timed event to happen.
     */
    public FixedTask(String description, String fixedDuration) {
        super(description);
        this.fixedDuration = fixedDuration;
    }

    /**
     * toString method for Fixed Task.
     *
     * @return Displays the task as [F], as well as "fixedDuration".
     */
    @Override
    public String toString() {
        return "[F]" + super.toString() + " (needs " + fixedDuration + ")";
    }
}