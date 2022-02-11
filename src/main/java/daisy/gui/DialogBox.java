package daisy.gui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

//@@author tsy24-reused
//Reused from https://se-education.org/guides/tutorials/javaFx.html
// with modifications
/**
 * Dialog box consisting of an ImageView to represent the speaker's face and a text
 * containing text from the speaker.
 */
public class DialogBox extends HBox {

    @FXML
    private Text textBox;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        textBox.setText(text);
        displayPicture.setClip(new Circle(30, 30, 30));
        displayPicture.setImage(img);
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
     * Returns a DialogBox containing text and image of user.
     *
     * @param text Text string from user.
     * @param img Image of user.
     * @return DialogBox containing text and image of user.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }
    /**
     * Returns a flipped DialogBox containing response and image of Daisy.
     *
     * @param text Response string from Daisy.
     * @param img Image of Daisy.
     * @return DialogBox containing response and image of Daisy.
     */
    public static DialogBox getDaisyDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.textBox.setTextAlignment(TextAlignment.LEFT);
        return db;
    }
    /**
     * Returns a flipped DialogBox containing error message and image of Daisy.
     *
     * @param text Error message string from Daisy.
     * @param img Image of Daisy.
     * @return DialogBox containing error message and image of Daisy.
     */
    public static DialogBox getExceptionDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.textBox.setFill(Color.RED);
        db.textBox.setTextAlignment(TextAlignment.LEFT);
        return db;
    }
}
