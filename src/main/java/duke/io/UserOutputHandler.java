package duke.io;

import duke.messages.Message;

/**
 * Defines the behaviour required to handle output to the user.
 *
 * @author kevin9foong
 */
public interface UserOutputHandler {
    void handleOutput(Message msg);
}
