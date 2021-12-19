package abyss.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class AbyssDialog extends HBox {
    @FXML
    private Label abyssDialog;
    @FXML
    private ImageView displayPicture;

    private AbyssDialog(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/AbyssDialog.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        abyssDialog.setText(text);
        displayPicture.setImage(img);
    }

    public static AbyssDialog getDialog(String text, Image img) {
        var db = new AbyssDialog(text, img);
        return db;
    }
}
