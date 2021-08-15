/**
 * Enum file for chatbot commands.
 *
 * @author marcuspeh
 * @version A-Enums
 * @since 15 Aug 2021
 */
public enum Keyword {
    /** Exit message. */
    EXIT("bye"),
    /** Keyword for listing out all the task. */
    LIST("list"),
    /** Keyword for marking task as done. */
    DONE("done"),
    /** Keyword for marking task as Deadline task." */
    DEADLINE("deadline", " /by "),
    /** Keyword for marking task as Events. */
    EVENTS("event", " /at "),
    /** Keyword for marking task as Todos. */
    TODOS("todo"),
    /** Keyword for deleting task. */
    DELETE("delete");

    /** Keyword for command. */
    private String keyword;
    /** Separator for spliting up various input. */
    private String separator;

    /**
     * Constructor for Keyword Enum.
     *
     * @param keyword Keyword for command
     */
    Keyword(String keyword) {
        this.keyword = keyword;
        this.separator = "";
    }

    /**
     * Constructor for Keyword Enum.
     *
     * @param keyword Keyword for command
     * @param separator Separator for spliting up various input
     */
    Keyword(String keyword, String separator) {
        this.keyword = keyword;
        this.separator = separator;
    }

    /**
     * Find the length of the keyword.
     *
     * @return the length of the keyword.
     */
    public int length() {
        return keyword.length();
    }

    /**
     * Getter for keyword.
     *
     * @return keyword for command.
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * Getter for separator.
     *
     * @return separator for command.
     */
    public String getSeparator() {
        return separator;
    }
}
