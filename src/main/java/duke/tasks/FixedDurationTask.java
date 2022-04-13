package duke.tasks;

import duke.exceptions.NoSuchCommandException;

public class FixedDurationTask extends Task {

    private final String duration;

    public FixedDurationTask(String name, boolean done, String duration) {
        super(name, done);
        this.duration = duration;
    }

    @Override
    public String toString() {
        return String.format("[F][%s] %s (needs: %s)", (getDone()) ? "X" : " ", getName(), this.duration);
    }

    @Override
    public String toStringOutput() {
        return String.format("%s | %s | %s | %s", "F", (getDone()) ? "X" : "O", getName(), this.duration);
    }

    public static void isLegitInput(String input) throws NoSuchCommandException {
        if (input.split("/duration").length != 2) {
            throw new NoSuchCommandException("There should be exactly one '/duration' in statement with end date/time after");
        } else if (input.split("/duration")[0].split("fixed")[1].strip() == "") {
            throw new NoSuchCommandException("Could not find name of Task");
        }
    }

    public static String getNameInput(String input) {
        return input.split("/duration")[0].split("fixed")[1].strip();
    }

    public static String getDurationInput(String input) {
        return input.split("/duration")[1].strip();
    }
}
