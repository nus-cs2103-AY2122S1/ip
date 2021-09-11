package duke.gui;

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
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

/**
 * This class represents a dialog box consisting of an ImageView to represent the duke's and user's face and
 * a label containing text from the user and duke.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            setUpDialogBox(text, img);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setUpDialogBox(String text, Image img) throws IOException {
        String resourceString = "/view/DialogBox.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource(resourceString));

        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        fxmlLoader.load();

        dialog.setText(text);
        dialog.setFont(Font.font("Comic Sans MS", 13));
        displayPicture.setImage(img);
    }

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Returns the dialog box when user type in something.
     *
     * @param text User's input
     * @param img User's icon
     * @return A DialogBox for the user.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.setStyle("-fx-background-color: #000042; -fx-border-color: #FFFFFF");
        return db;
    }

    /**
     * Returns the dialog box when chatBot replies something.
     *
     * @param text ChatBot's input
     * @param img ChatBot's icon
     * @return A DialogBox for the chatBot.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.setStyle("-fx-background-color: #4b0908; -fx-border-color: #b97d10");
        db.flip();
        return db;
    }
}
