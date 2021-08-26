package duke.controller.dialog;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

/**
 * Controller for DukeDialog.
 */
public class DukeDialogController extends DialogController {
    private static final double LAYOUT_X = 75;
    private static final double LAYOUT_Y = 8;

    @FXML
    private Pane dialog;

    /**
     * Constructs a DukeDialogController object.
     *
     * @param text Content will be shown.
     * @throws IOException If Fxml resource does not exist.
     */
    public DukeDialogController(String text)
            throws IOException {
        super("/view/DukeDialog.fxml");
        addMessageBox(dialog, text, LAYOUT_X, LAYOUT_Y);
    }

    /**
     * Constructs a DukeDialogController object.
     *
     * @param text Content will be shown.
     * @param height DukeDialogController height.
     * @throws IOException If Fxml resource does not exist.
     */
    public DukeDialogController(String text, double height)
            throws IOException {
        super("/view/DukeDialog.fxml", height);
        addMessageBox(dialog, text, LAYOUT_X, LAYOUT_Y);
    }
}
