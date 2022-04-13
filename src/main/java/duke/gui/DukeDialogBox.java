package duke.gui;

import java.io.IOException;

import duke.MainWindow;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

/**
 * This control represents a dialog box consisting of a Circle filled with an image to represent Duke
 * and a Text containing reply from Duke.
 *
 * Taken / adapted from Seedu JavaFX Tutorial Part4
 */
public class DukeDialogBox extends HBox {
    @FXML
    private Text dialog;
    @FXML
    private VBox chatVBox;
    @FXML
    private Circle displayPicture;

    private DukeDialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DukeDialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(text);
        displayPicture.setFill(new ImagePattern(img));
    }

    /**
     * Return a new DukeDialogBox with the set image and text.
     *
     * @param text The text to fill the dialog box
     * @param img The image representing Duke.
     * @return DukeDialogBox with the image and text given.
     */
    public static DukeDialogBox getDukeDialog(String text, Image img) {
        return new DukeDialogBox(text, img);
    }

}
