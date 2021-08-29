package duke.io;

import duke.messages.Message;
import javafx.scene.control.TextArea;

public class GuiUserOutputHandler implements UserOutputHandler {
    private final TextArea userOutputTextArea;

    public GuiUserOutputHandler(TextArea userOutputTextArea) {
        this.userOutputTextArea = userOutputTextArea;
    }

    @Override
    public void writeMessage(Message msg) {
        this.userOutputTextArea.setText(msg.toString() + "\n"
                + userOutputTextArea.getText());
    }
}
