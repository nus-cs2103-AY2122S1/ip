package duke;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private TextArea dialog;
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

        dialog.setText(text);
        displayPicture.setClip(new Circle(50, 50, 50));
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
     * Create a user dialog box.
     *
     * @return a user dialog box with user's input message.
     * */
    public static DialogBox getUserDialog(String text, Image img) {
        var box = new DialogBox(text, img);
        Background background = new Background(new BackgroundFill(Paint.valueOf("#00bfff"),
                new CornerRadii(30.0), Insets.EMPTY));
        box.setBackground(background);
        return box;
    }

    /**
     * Create a response dialog box.
     *
     * @return a response dialog box with the result of executing user's command.
     * */
    public static DialogBox getDukeDialog(String text, Image img) {
        var box = new DialogBox(text, img);
        Background background = new Background(new BackgroundFill(Paint.valueOf("#7fffd4"),
                new CornerRadii(30.0), Insets.EMPTY));
        box.setBackground(background);
        box.flip();
        return box;
    }
}
