// Credit: Chan Jun Da
package duke.gui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog bo consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {

    private static final double SPACING = 10;


    /**
     * Represents a dialog box.
     * @param text dialog content
     * @param imageView profile
     * @param isLeft true for Duke dialogBox, false for user dialogBox
     */
    public DialogBox(String text, ImageView imageView, boolean isLeft) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/views/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        TextBox output = isLeft
                ? TextBox.leftwardTextBox(text)
                : TextBox.rightwardTextBox(text);

        imageView.setClip(new Circle(50, 50, 50));
        imageView.setFitWidth(100.0);
        imageView.setFitHeight(100.0);
        getChildren().addAll(output, imageView);

        setAlignment(Pos.TOP_RIGHT);
        setSpacing(SPACING);
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
     * Gets a user dialog box.
     *
     * @param text dialog content
     * @param imageView user profile
     * @return an user dialog box
     */
    public static DialogBox getUserDialog(String text, ImageView imageView) {
        return new DialogBox(text, imageView, false);
    }

    /**
     * Gets a Duke dialog box.
     *
     * @param text dialog content
     * @param imageView Duke profile
     * @return a Duke dialog box
     */
    public static DialogBox getDukeDialog(String text, ImageView imageView) {
        var db = new DialogBox(text, imageView, true);
        db.flip();
        return db;
    }
}
