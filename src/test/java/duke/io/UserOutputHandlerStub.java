package duke.io;

import duke.messages.Message;

public class UserOutputHandlerStub implements UserOutputHandler {
    private String writtenMessage;

    @Override
    public void writeMessage(Message msg) {
        this.writtenMessage = msg.toString();
    }

    public String getWrittenMessage() {
        return this.writtenMessage;
    }
}
