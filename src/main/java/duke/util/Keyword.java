package duke.util;

/**
 * Enum file for chatbot commands.
 *
 * @author marcuspeh
 * @version Level-9
 * @since 19 Aug 2021
 */
public enum Keyword {
    /** Exit message. */
    EXIT("bye"),
    /** duke.util.Keyword for listing out all the task. */
    LIST("list"),
    /** duke.util.Keyword for marking task as done. */
    DONE("done"),
    /** duke.util.Keyword for marking task as Deadline task." */
    DEADLINE("deadline", " /by ", "D"),
    /** duke.util.Keyword for marking task as duke.task.Events. */
    EVENTS("event", " /at ", "E"),
    /** duke.util.Keyword for marking task as Todos. */
    TODOS("todo", "", "T"),
    /** duke.util.Keyword for deleting task. */
    DELETE("delete"),
    /** duke.util.Keyword for finding task. */
    FIND("find"),
    /** duke.util.Keyword for help. */
    HELP("help");

    /** duke.util.Keyword for command. */
    private String keyword;
    /** Separator for spliting up various input. */
    private String separator;
    /** Code for saving task. */
    private String saveWord;

    /**
     * Constructor for duke.util.Keyword Enum.
     *
     * @param keyword duke.util.Keyword for command
     */
    Keyword(String keyword) {
        this.keyword = keyword;
        separator = "";
        saveWord = "";
    }

    /**
     * Constructor for duke.util.Keyword Enum.
     *
     * @param keyword duke.util.Keyword for command.
     * @param separator Separator for spliting up various input.
     * @param saveWord saveWord for task.
     */
    Keyword(String keyword, String separator, String saveWord) {
        this.keyword = keyword;
        this.separator = separator;
        this.saveWord = saveWord;
    }

    /**
     * Finds the length of the keyword.
     *
     * @return the length of the keyword.
     */
    public int length() {
        return keyword.length();
    }

    public String getKeyword() {
        return keyword;
    }

    public String getSeparator() {
        return separator;
    }

    public String getSaveWord() {
        return saveWord;
    }
}
