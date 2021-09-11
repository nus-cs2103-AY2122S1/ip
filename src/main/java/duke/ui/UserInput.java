package duke.ui;

import javafx.scene.control.TextField;

public class UserInput extends TextField {

    private static final double FIELD_WIDTH = 325.0;

    /**
     * Creates a UserInput with the specified width
     */
    public UserInput() {
        super();
        this.setPrefWidth(FIELD_WIDTH);
    }
}
