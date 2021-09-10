package type;

/**
 * Encapsulates valid values for command type.
 */
public enum CommandTypeEnum {
    BYE("bye", "exit the app", "bye"),
    DEADLINE("deadline",
            "add a task with a deadline",
            String.format("deadline <description> /by <%s>", DatetimeTypeEnum.INPUT.toString())
    ),
    DELETE("delete", "delete a task", "delete <task number in last shown list>"),
    DONE("done", "mark a task as done", "done <task number in last shown list>"),
    EVENT("event", "add a task occurring at a time", "event <description> /at <time>"),
    FIND("find", "find all tasks containing a keyword", "find <keyword>"),
    HELP("help", "list all available commands", "help"),
    LIST("list", "list all tasks", "list"),
    TODO("todo", "add a task with a description", "todo <description>");

    private String value;
    private String usage;
    private String format;

    CommandTypeEnum(String value, String usage, String format) {
        this.value = value;
        this.usage = usage;
        this.format = format;
    }

    /**
     * Returns string representation of a command type enum.
     *
     * @return String representation of a command type enum
     */
    @Override
    public String toString() {
        return this.value;
    }

    /**
     * Returns full description of the command, its usage and input format.
     *
     * @return full description of the command, its usage and input format.
     */
    public String toFullInfoString() {
        return this.value
                + "\n\t" + "usage: " + this.usage
                + "\n\t" + "format: " + this.format;
    }

    /**
     * Gets format of command.
     *
     * @return format of command.
     */
    public String getFormat() {
        return this.format;
    }
}
