package duke.command;
/**
 * This encapsulates all Commands that can be understood by Duke.
 */
public abstract class Command {

    public abstract String getResponse(String input);

    public boolean canExit() {
        return false;
    };
}

