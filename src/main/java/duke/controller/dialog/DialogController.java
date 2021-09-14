package duke.controller.dialog;

import java.io.IOException;

import duke.controller.DukeController;
import duke.controller.component.MessageBox;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * Controller for DukeDialog.
 */
public class DialogController extends HBox {

    /**
     * Constructs a DukeDialogController object.
     *
     * @param resource Fxml resource.
     * @throws IOException If Fxml resource does not exist.
     */
    public DialogController(String resource) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DukeController.class.getResource(resource));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        fxmlLoader.load();
    }

    /**
     * Constructs a DukeDialogController object.
     *
     * @param resource Fxml resource.
     * @param height DukeDialogController height.
     * @throws IOException If Fxml resource does not exist.
     */
    public DialogController(String resource, double height) throws IOException {
        setMinHeight(height);
        FXMLLoader fxmlLoader = new FXMLLoader(DukeController.class.getResource(resource));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        fxmlLoader.load();
    }

    /**
     * Adds MessageBox to dialog.
     *
     * @param dialog Dialog for the MessageBox.
     * @param text Content that will be shown in the dialog.
     * @param color Content text color.
     * @param layoutX MessageBox layoutX.
     * @param layoutY MessageBox layoutY.
     */
    public void addMessageBox(
            Pane dialog, String text, Color color, double layoutX, double layoutY) {
        // The reason of following approach is because the size of the messageBox
        // will be returned only after it is added by the dialog.
        MessageBox preMessageBox = new MessageBox(text, layoutX, layoutY);
        preMessageBox.heightProperty()
                .addListener(observable -> {
                    MessageBox messageBox = new MessageBox(
                            text, color, layoutX, layoutY, preMessageBox.getHeight());
                    dialog.getChildren().add(messageBox);
                    dialog.getChildren().remove(preMessageBox);
                });
        dialog.getChildren().add(preMessageBox);
    }
}
