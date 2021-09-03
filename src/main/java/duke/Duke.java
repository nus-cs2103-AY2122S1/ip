package duke;

import java.io.IOException;

import duke.exception.DukeException;
import duke.misc.Parser;
import duke.misc.TaskList;

/**
 * Driver class to simulate the 'Annie' chat bot program.
 */
public class Duke {
    private final TaskList tl;
    private final Parser p;
    private boolean isExited;

    /**
     * Constructor for Duke class.
     *
     * @throws IOException In case of invalid directory.
     */
    public Duke() throws IOException {
        tl = new TaskList();
        tl.initialise();
        p = new Parser();
        isExited = false;
    }

    public boolean getIsExited() {
        return isExited;
    }

    /**
     * Returns an appropriate text reply for duke in the dialog box according
     * to user's input message.
     *
     * @param input The user's input.
     * @return A text that duke should reply with.
     */
    public String getResponse(String input) {
        if (input.equals("bye")) {
            isExited = true;
        }
        try {
            return p.executeCommand(input, tl);
        } catch (DukeException | IOException e) {
            return e.toString();
        }
    }
}
