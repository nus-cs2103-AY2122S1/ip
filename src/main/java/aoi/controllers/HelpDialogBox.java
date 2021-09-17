package aoi.controllers;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.shape.Circle;

/**
 * An example of a custom control using FXML.
 * This control represents a help dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class HelpDialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private Hyperlink hyperlink;
    @FXML
    private ImageView displayPicture;
    private final Hyperlink link = new Hyperlink("https://mslevis.github.io/ip/");

    private HelpDialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/HelpDialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        dialog.getStyleClass().add("dialog-box");
        hyperlink.getStyleClass().add("dialog-box");
        this.setHgrow(dialog, Priority.ALWAYS);
        displayPicture.setImage(img);
        displayPicture.setClip(new Circle(50, 40, 40));
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    public static HelpDialogBox getHelpDialog(String text, Image img) {
        var db = new HelpDialogBox(text, img);
        db.flip();
        return db;
    }
}
