package duke.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Collections;

public class DialogBox extends HBox {

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private static final String[] dukeStyleClasses = {"duke-dialog-background"};
    private static final String[] userStyleClasses = {"user-dialog-background"};

    /**
     * Constructor fo DialogBox
     *
     * @param text the input text
     * @param img the image to be attached
     * @param styleClass the style to follow
     */
    private DialogBox(String text, Image img, String... styleClass) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        dialog.getStyleClass().addAll(styleClass);
        displayPicture.setImage(img);
        displayPicture.setClip(new Circle(30,30,30));
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

    /**
     * Gets the DialogBox for user
     *
     * @param text the input message form user
     * @param img the user image
     * @return the DialogBox with a String and Image
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, userStyleClasses);
    }

    /**
     * Gets the DialogBox for Duke to respond
     *
     * @param text the response duke will give
     * @param img the image of duke
     * @return the DialogBox with duke response and the image
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img, dukeStyleClasses);
        db.flip();
        return db;
    }
}
