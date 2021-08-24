package duke.command;

public enum Commands {
    /**
     * duke.command.Commands that duke.Duke might use.
     */
    GREET(String.format("Hello! I'm duke.Duke\n%4sWhat can I do for you?", " ")),
    ADD("Got it. I've added this duke.task:"),
    DONE("Nice! I've marked this duke.task as done:"),
    DELETE("Noted. I've removed this duke.task:"),
    LIST("Here are the tasks in your list:"),
    EXIT("Bye. Hope to see you again soon!");

    private final String command;

    private Commands(String command) {
        this.command = command;
    }

    public String getCommand() {
        return this.command;
    }
}
