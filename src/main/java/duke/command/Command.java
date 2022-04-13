package duke.command;

import duke.ui.Ui;

/**
 * Command abstract class.
 */
public abstract class Command {
    protected String commandName;
    protected String description;
    protected String[] arguments;

    /**
     * Executes the action that the command represents
     *
     * @return String to display as a response
     */
    public abstract String execute();

    /**
     * Represents Command as a string.
     *
     * @return Command name, description, as well as its arguments on newlines.
     */
    @Override
    public String toString() {
        assert commandName != null : "Command Name cannot be null";
        assert description != null : "Description cannot be null";
        assert arguments != null : "Arguments cannot be null";

        StringBuilder argString = new StringBuilder();
        for (String arg : arguments) {
            argString.append(Ui.OUTPUT_DISPLAY).append(arg).append('\n');
        }
        return commandName + " - " + description + '\n'
                + argString + '\n';
    }
}
