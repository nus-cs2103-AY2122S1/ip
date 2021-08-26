package duke.controller.dialog;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

/**
 * Controller for UserDialog.
 */
public class UserDialogController extends DialogController {
    private static final double LAYOUT_X = 115;
    private static final double LAYOUT_Y = 8;

    @FXML
    private Pane dialog;

    /**
     * Constructs a UserDialogController object.
     *
     * @param text Content will be shown.
     * @throws IOException If Fxml resource does not exist.
     */
    public UserDialogController(String text)
            throws IOException {
        super("/view/UserDialog.fxml");
        addMessageBox(dialog, text, LAYOUT_X, LAYOUT_Y);
    }

    /**
     * Constructs a UserDialogController object.
     *
     * @param text Content will be shown.
     * @param height UserDialogController height.
     * @throws IOException If Fxml resource does not exist.
     */
    public UserDialogController(String text, double height)
            throws IOException {
        super("/view/UserDialog.fxml", height);
        addMessageBox(dialog, text, LAYOUT_X, LAYOUT_Y);
    }
}
