package duke.command;

public abstract class Command {
    /**
     * Indicate what DuC will respond after each enter
     * command cycle
     * @return the response of Duke
     */
    public abstract String reply();
}
