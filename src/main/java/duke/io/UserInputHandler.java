package duke.io;

import java.io.IOException;

/**
 * Defines the behaviour required to handle support user input.
 *
 * @author kevin9foong
 */
public interface UserInputHandler {
    String readInput() throws IOException;
}
