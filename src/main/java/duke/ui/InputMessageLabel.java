package duke.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;

public class InputMessageLabel extends MessageLabel {

    private static final Insets INPUT_MSG_INSETS = new Insets(10, 10, 0, 100);
    private static final String PERSON_IMG_PATH = "/images/personicon.png";

    /**
     * Creates an InputMessageLabel with the given message and specified insets.
     *
     * @param msg the given message.
     */
    public InputMessageLabel(String msg) {
        super(msg, PERSON_IMG_PATH, INPUT_MSG_INSETS);
        this.setAlignment(Pos.TOP_RIGHT);
        this.setContentDisplay(ContentDisplay.RIGHT);
    }
}
