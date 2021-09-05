package duke.io;

import duke.messages.Message;

/**
 * Handles writing to output destination.
 *
 * @author kevin9foong
 */
public class ConsoleUserOutputHandler implements UserOutputHandler {

    /**
     * Writes specified message to the standard output.
     *
     * @param msg <code>Message</code> containing String data to be written as output.
     */
    public void handleOutput(Message msg) {
        System.out.println(msg.toString());
    }
}

