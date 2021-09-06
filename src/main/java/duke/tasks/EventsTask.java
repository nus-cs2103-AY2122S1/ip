package duke.tasks;

import duke.exceptions.NoSuchCommandException;

public class EventsTask extends Task {

    private final String startend;

    public EventsTask(String name, boolean done, String startend) {
        super(name, done);
        this.startend = startend;
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)", (getDone()) ? "X" : " ", getName(), this.startend);
    }

    @Override
    public String toStringOutput() {
        return String.format("%s | %s | %s | %s", "E", (getDone()) ? "X" : "O", getName(), this.startend);
    }

    public static void isLegitInput(String input) throws NoSuchCommandException {
        if (input.split("/at").length != 2) {
            throw new NoSuchCommandException(
                    "There should be exactly one '/at' in statement with start and end date/time after");
        } else if (input.split("/at")[0].split("event")[1].strip() == "") {
            throw new NoSuchCommandException("Could not find name of Task");
        }
    }

    public static String getNameInput(String input) {
        return input.split("/at")[0].split("event")[1].strip();
    }

    public static String getDeadlineInput(String input) {
        return input.split("/at")[1].strip();
    }
}
