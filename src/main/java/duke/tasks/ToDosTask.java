package duke.tasks;

import duke.exceptions.NoSuchCommandException;

public class ToDosTask extends Task {
    public ToDosTask(String name, boolean done) {
        super(name, done);
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", (getDone()) ? "X" : " ", getName());
    }

    @Override
    public String toStringOutput() {
        return String.format("%s | %s | %s", "T", (getDone()) ? "X" : "O", getName());
    }

    public static String getNameInput(String input) {
        
        return input.split("todo")[1].strip();
    }

    public static void isLegitInput(String input) throws NoSuchCommandException {
        if (input.strip().equals("todo")) {
            throw new NoSuchCommandException("Could not find name of Task");
        }
    }
}
