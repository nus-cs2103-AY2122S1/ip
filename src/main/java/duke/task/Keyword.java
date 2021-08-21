package duke.task;

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
    /** duke.task.Keyword for listing out all the task. */
    LIST("list"),
    /** duke.task.Keyword for marking task as done. */
    DONE("done"),
    /** duke.task.Keyword for marking task as Deadline task." */
    DEADLINE("deadline", " /by ", "D"),
    /** duke.task.Keyword for marking task as duke.task.Events. */
    EVENTS("event", " /at ", "E"),
    /** duke.task.Keyword for marking task as Todos. */
    TODOS("todo", "", "T"),
    /** duke.task.Keyword for deleting task. */
    DELETE("delete");

    /** duke.task.Keyword for command. */
    private String keyword;
    /** Separator for spliting up various input. */
    private String separator;
    /** Code for saving task. */
    private  String saveWord;

    /**
     * Constructor for duke.task.Keyword Enum.
     *
     * @param keyword duke.task.Keyword for command
     */
    Keyword(String keyword) {
        this.keyword = keyword;
        this.separator = "";
        this.saveWord = "";
    }

    /**
     * Constructor for duke.task.Keyword Enum.
     *
     * @param keyword duke.task.Keyword for command.
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
