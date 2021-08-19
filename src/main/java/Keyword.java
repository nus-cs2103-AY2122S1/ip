/**
 * Enum file for chatbot commands.
 *
 * @author marcuspeh
 * @version Level-7
 * @since 19 Aug 2021
 */
public enum Keyword {
    /** Exit message. */
    EXIT("bye"),
    /** Keyword for listing out all the task. */
    LIST("list"),
    /** Keyword for marking task as done. */
    DONE("done"),
    /** Keyword for marking task as Deadline task." */
    DEADLINE("deadline", " /by ", "D"),
    /** Keyword for marking task as Events. */
    EVENTS("event", " /at ", "E"),
    /** Keyword for marking task as Todos. */
    TODOS("todo", "", "T"),
    /** Keyword for deleting task. */
    DELETE("delete");

    /** Keyword for command. */
    private String keyword;
    /** Separator for spliting up various input. */
    private String separator;
    /** Code for saving task. */
    private  String saveWord;

    /**
     * Constructor for Keyword Enum.
     *
     * @param keyword Keyword for command
     */
    Keyword(String keyword) {
        this.keyword = keyword;
        this.separator = "";
        this.saveWord = "";
    }

    /**
     * Constructor for Keyword Enum.
     *
     * @param keyword Keyword for command.
     * @param separator Separator for spliting up various input.
     * @param saveWord saveWord for task.
     */
    Keyword(String keyword, String separator, String saveWord) {
        this.keyword = keyword;
        this.separator = separator;
        this.saveWord = saveWord;
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

    /**
     * Getter for saveWord.
     *
     * @return saveWord for task.
     */
    public String getSaveWord() {
        return saveWord;
    }
}
