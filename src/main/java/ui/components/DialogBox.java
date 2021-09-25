package ui.components;

import java.io.IOException;
import java.util.Collections;

import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/**
 * Structure for message bubble of both user and Alice.
 *
 * @author https://se-education.org/guides/tutorials/javaFx.html
 */
public class DialogBox extends HBox {

    @FXML
    private Label dialog = new Label();
    @FXML
    private Circle displayPicture = new Circle();

    /**
     * Constructor for the dialog box.
     */
    private DialogBox(String text, Image image) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // animation for text typing
        // tutorial by www.java2s.com
        Animation animation = new Transition() {
            {
                setCycleDuration(Duration.millis(1000));
            }

            @Override
            protected void interpolate(double frac) {
                // cast to float so Math round to int
                int n = Math.round(text.length() * (float) frac);
                dialog.setText(text.substring(0, n));
            }
        };


        displayPicture.setRadius(50);
        displayPicture.setFill(new ImagePattern(image));

        animation.play();
        this.setAlignment(Pos.TOP_RIGHT);
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
     * Factory method for producing DialogBox.
     *
     * @param text the message to be printed in the DialogBox.
     * @param img  image to use as the avatar.
     * @return DialogBox with message to the left and avatar to the right.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Return a DialogBox object for Alice.
     *
     * @return DialogBox for Alice.
     */
    public static DialogBox getAliceDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
