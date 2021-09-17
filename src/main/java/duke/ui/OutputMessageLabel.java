package duke.ui;

import javafx.geometry.Insets;

public class OutputMessageLabel extends MessageLabel {

    private static final Insets OUTPUT_MSG_INSETS = new Insets(10, 100, 0, 10);
    private static final String BOB_IMG_PATH = "/images/bobthebuilder.jpg";

    public OutputMessageLabel(String msg) {
        super(msg, BOB_IMG_PATH, OUTPUT_MSG_INSETS);
    }
}
