package duke.ui;

import javafx.geometry.Insets;

public class ErrorMessageLabel extends MessageLabel {

    private static final Insets ERROR_MSG_INSETS = new Insets(10, 100, 0, 10);
    private static final String ERROR_IMG_PATH = "/images/erroricon.png";

    public ErrorMessageLabel(String msg) {
        super(msg, ERROR_IMG_PATH, ERROR_MSG_INSETS);
    }
}
