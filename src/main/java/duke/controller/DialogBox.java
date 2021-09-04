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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.util.Collections;

import static javafx.scene.paint.Color.color;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;
    @FXML
    private HBox message;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        dialog.setWrapText(true);

        displayPicture.setImage(img);
        displayPicture.setClip(new Circle(25, 25, 25));

    }

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Gets the user input and transform it into a text bubble.
     * @param s user input into the Textfield.
     * @param iv pre-loaded profile picture of the user.
     * @return DialogBox that encompasses the user input and the profile picture.
     */
    public static DialogBox getUserDialog(String s, Image iv) {
        DialogBox db = new DialogBox(s, iv);
        db.message.setBackground(new Background(new BackgroundFill(color(0.2, 0.58, 1),
                new CornerRadii(15),
                null)));
        return db;
    }

    /**
     * Gets the duke response adn transforms it into a text bubble.
     * @param s duke response based on user input.
     * @param iv pre-loaded picture of Duke.
     * @return DialogBox that encompasses duke response and duke picture.
     */
    public static DialogBox getDukeDialog(String s, Image iv) {
        DialogBox db = new DialogBox(s, iv);
        db.flip();
        db.message.setBackground(new Background(new BackgroundFill(color(0.9, 0.9, 0.9),
                new CornerRadii(15),
                null)));
        return db;
    }

    /**
     * Transform error messages from Duke into a brighter colour to get attention
     * of user.
     */
    public void modifyColorForError() {
        this.dialog.setTextFill(color(1,1,1));
        this.setBackground(new Background(new BackgroundFill(color(1, 0, 0),
                new CornerRadii(15),
                null)));
    }

}