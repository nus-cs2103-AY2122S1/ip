package duke.io;

import duke.messages.Message;

public interface UserOutputHandler {
    void writeMessage(Message msg);
}
