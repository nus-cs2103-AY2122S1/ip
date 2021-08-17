/**
 * This class encapsulates a reply from Duke.
 *
 * @author Kleon Ang
 */
public class Reply {
    private String content;
    private final String LINE = "\t____________________________________________________________";

    /**
     * Constructor for a Reply.
     *
     * @param content A string containing the contents of Duke's reply.
     */
    public Reply(String content) {
        this.content = content.replaceAll("(?m)^", "\t ");;
    }

    /**
     * Formats Duke's reply on the command-line interface.
     *
     * @return A string containing the formatted reply from Duke.
     */
    @Override
    public String toString() {
        return this.LINE + "\n" + this.content + "\n" + this.LINE;
    }
}
