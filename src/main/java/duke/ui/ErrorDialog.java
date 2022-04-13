package duke.ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * This control represents a dialog box consisting of an error message to be displayed
 * more prominently than a normal message.
 */
public class ErrorDialog extends HBox {
    @FXML
    private Label dialog;

    /**
     * Private constructor to create an error dialog.
     *
     * @param text Text to display.
     */
    private ErrorDialog(String text) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/ErrorDialog.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
    }

    /**
     * Creates an ErrorDialog object. Factory method for ErrorDialog.
     *
     * @param text Text to display.
     * @return New ErrorDialog object.
     */
    public static ErrorDialog getErrorDialog(String text) {
        var db = new ErrorDialog(text);
        return db;
    }
}
