package duke.javafx;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    private final boolean isWarning;
    @FXML
    private Label dialog;
    @FXML
    private Circle circle;

    private DialogBox(String text, Image img, boolean isWarning) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        this.isWarning = isWarning;

        if (this.isWarning) {
            dialog.setTextFill(Color.color(1, 0, 0));
        }
        circle.setFill(new ImagePattern(img));
    }

    public static DialogBox getUserDialog(String text, Image img, boolean isWarning) {
        return new DialogBox(text, img, isWarning);
    }

    public static DialogBox getDukeDialog(String text, Image img, boolean isWarning) {
        var db = new DialogBox(text, img, isWarning);
        db.flip();
        return db;
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialog.setStyle("-fx-border-radius: 0 10 10 10; -fx-border-color: #A0E7E5; -fx-border-width: 2; "
                + "-fx-background-color: white; -fx-background-radius: 0 10 10 10;");
    }
}
