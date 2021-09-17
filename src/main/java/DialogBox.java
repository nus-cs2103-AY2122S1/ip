import java.io.IOException;

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
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class DialogBox extends HBox {
    @FXML
    private Label dialog;
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
        dialog.setTextFill(Color.DARKGREEN);
        displayPicture.setImage(img);
        Circle circle = new Circle(50, 50, 50);
        displayPicture.setClip(circle);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    public void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
        dialog.setTextFill(Color.DARKBLUE);
    }

    public void setErrorDialog() {
        dialog.setTextFill(Color.DARKRED);
    }

    /**
     * Returns DialogBox containing the user input.
     * @param text input string of user.
     * @param img user's profile picture.
     * @return DialogBox containing user's input
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Returns DialogBox containing Duke's response to user input.
     * @param text String containing Duke's response.
     * @param img Duke's profile picture.
     * @return DialogBox containing Duke's response.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.flip();
        db.setStyle("-fx-background-color: #98FB98; -fx-border-width: 5; -fx-border-color: white;");
        return db;
    }

    /**
     * Returns DialogBox containing Duke's error response to user input.
     * @param text String containing Duke's error message.
     * @param img Duke's profile picture.
     * @return DialogBox containing Duke's error message.
     */
    public static DialogBox getErrorDialog(String text, Image img) {
        DialogBox db = getDukeDialog(text, img);
        db.setErrorDialog();
        return db;
    }
}
