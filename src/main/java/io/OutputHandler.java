package io;

import messages.Message;

/**
 * Class handles writing to output source.
 *
 * @author kevin9foong
 */
public class OutputHandler {
    public void writeMessage(Message msg) {
        System.out.println(msg.toString());
    }
}

