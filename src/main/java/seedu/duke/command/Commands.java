package seedu.duke.command;

/**
 * Encapsulates the possible commands that would be output
 * by a <code>Duke</code> object.
 */
public enum Commands {
    /**
     * Commands that Duke might use.
     */
    GREET(String.format("Hello! I'm Duke\n%4sWhat can I do for you?", " ")),
    ADD("Got it. I've added this task:"),
    FIND("Here are the matching tasks in your list."),
    DONE("Nice! I've marked this task as done:"),
    DELETE("Noted. I've removed this task:"),
    LIST("Here are the tasks in your list:"),
    EXIT("Bye. Hope to see you again soon!");

    private final String command;

    private Commands(String command) {
        this.command = command;
    }

    /**
     * Returns the string description of the command.
     *
     * @return String representation of command.
     */
    public String getCommand() {
        return this.command;
    }
}
