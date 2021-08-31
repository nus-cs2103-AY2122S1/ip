package Duke;

import java.io.IOException;

/**
 * An UI that recieve's user's input to run Duke
 * and prints out the welcome message to the user.
 * UI also carries the parser created.
 */
public class Recieve {
    private Parser parser;

    /**
     * A public constructor to create Recieve.
     * @param parser The parser created when user starts session with Duke.
     */
    public Recieve(Parser parser) {
        this.parser = parser;
    }

    /**
     * Runs the Duke Application.
     *
     * @param input The user's input.
     * @return Returns the string representation of the response from Duke.
     */
    public String run(String input) {

            try {
                parser.getStorage().load();

                if (input.equals("bye")) {
                    return parser.endSession();
                } else if (input.startsWith("done ") || input.startsWith("done")) {
                    return parser.done(input);
                } else if (input.equals("list")) {
                    return parser.list();
                } else if (input.startsWith("delete ") || input.startsWith("delete")) {
                    return parser.delete(input);
                } else if (input.startsWith("find ") || input.startsWith("find")) {
                    return parser.find(input);
                } else {
                    return parser.add(input);
                }
            } catch (DukeException | InvalidTaskIndexException |
                    InvalidFormatException | IOException e) {
                return e.toString();
            }
        }


}
