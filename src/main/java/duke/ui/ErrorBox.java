package duke.ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * This control represents a dialog box when the Duke logic results in an exception
 */
public class ErrorBox extends HBox {
    @FXML
    private Label dialog;

    /**
     * Constructor of ErrorBox
     *
     * @param text Text inside the dialog box
     */
    public ErrorBox(String text) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/ErrorBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
    }
}
