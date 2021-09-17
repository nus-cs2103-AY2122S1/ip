package hyddd.command;

/**
 * @@author Hang Zelin
 *
 * An abstract class which indicates an abstract command type.
 */
public abstract class Command {
    /**
     * Returns a String response from a specific command type.
     *
     * @return String value the specific command responses.
     */
    public abstract String returnResponse();
}
