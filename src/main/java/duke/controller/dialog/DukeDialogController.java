package duke.controller.dialog;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * Controller for DukeDialog.
 */
public class DukeDialogController extends DialogController {
    private static final double LAYOUT_X = 75;
    private static final double LAYOUT_Y = 8;
    private static final String DUKE_DIALOG_FXML_PATH = "/view/DukeDialog.fxml";

    @FXML
    private Pane dialog;

    /**
     * Constructs a DukeDialogController object.
     *
     * @param text Content will be shown.
     * @param color Content text color.
     * @throws IOException If Fxml resource does not exist.
     */
    public DukeDialogController(String text, Color color)
            throws IOException {
        super(DUKE_DIALOG_FXML_PATH);
        addMessageBox(dialog, text, color, LAYOUT_X, LAYOUT_Y);
    }

    /**
     * Constructs a DukeDialogController object.
     *
     * @param text Content will be shown.
     * @param color Content text color.
     * @param height DukeDialogController height.
     * @throws IOException If Fxml resource does not exist.
     */
    public DukeDialogController(String text, Color color, double height)
            throws IOException {
        super(DUKE_DIALOG_FXML_PATH, height);
        addMessageBox(dialog, text, color, LAYOUT_X, LAYOUT_Y);
    }
}
