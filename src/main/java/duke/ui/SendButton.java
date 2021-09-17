package duke.ui;

import javafx.scene.control.Button;

public class SendButton extends Button {

    private static final String BUTTON_TEXT = "Send";
    private static final Double BUTTON_WIDTH = 55.0;

    /**
     * Creates a sendButton with the specified text and width.
     */
    public SendButton() {
        super(BUTTON_TEXT);
        this.setPrefWidth(BUTTON_WIDTH);
    }
}
