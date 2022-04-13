package duke.tasks;

import duke.exceptions.NoSuchCommandException;

public class DeadlinesTask extends Task {

    private final String end;

    public DeadlinesTask(String name, boolean done, String end) {
        super(name, done);
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", (getDone()) ? "X" : " ", getName(), this.end);
    }

    @Override
    public String toStringOutput() {
        return String.format("%s | %s | %s | %s", "D", (getDone()) ? "X" : "O", getName(), this.end);
    }

    public static void isLegitInput(String input) throws NoSuchCommandException {
        if (input.split("/by").length != 2) {
            throw new NoSuchCommandException("There should be exactly one '/by' in statement with end date/time after");
        } else if (input.split("/by")[0].split("deadline")[1].strip() == "") {
            throw new NoSuchCommandException("Could not find name of Task");
        }
    }

    public static String getNameInput(String input) {
        return input.split("/by")[0].split("deadline")[1].strip();
    }

    public static String getDeadlineInput(String input) {
        return input.split("/by")[1].strip();
    }
}
