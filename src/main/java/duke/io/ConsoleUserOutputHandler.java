package duke.io;

import duke.messages.Message;

/**
 * Class handles writing to output destination.
 *
 * @author kevin9foong
 */
public class ConsoleUserOutputHandler implements UserOutputHandler {
    public void writeMessage(Message msg) {
        System.out.println(msg.toString());
    }
}

