package duke.command;

public abstract class Command {
    /**
     * Indicates what DuC will respond after each entered
     * command by user - common method to all subclasses.
     * @return the response of Duke
     */
    public abstract String reply();
}
