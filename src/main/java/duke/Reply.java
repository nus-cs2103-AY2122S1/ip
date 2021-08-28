package duke;

/**
 * This class encapsulates a reply from Duke.
 *
 * @author Kleon Ang
 */
public class Reply {
    private static final String LINE = "\t____________________________________________________________";
    private final String content;

    /**
     * Constructor for a Reply.
     *
     * @param content A string containing the contents of Duke's reply.
     */
    public Reply(String content) {
        this.content = content.replaceAll("(?m)^", "\t ");
    }

    /**
     * Formats Duke's reply on the command-line interface.
     *
     * @return A string containing the formatted reply from Duke.
     */
    @Override
    public String toString() {
        return LINE + "\n" + this.content + "\n" + LINE;
    }
}
