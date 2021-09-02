package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

/**
 * Class to create dialog box with user photo and message.
 */
public class DialogBox extends HBox {
    private Label text;
    private ImageView displayPicture;

    /**
     * Method to initialize DialogBox.
     *
     * @param label Label to hold message.
     * @param iv Imageview to hold photo
     */
    public DialogBox(Label label, ImageView iv) {
        text = label;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Returns a dialog box with appropriate message and photo.
     *
     * @param fullCommand Message of user/duke.
     * @param iv Image of user/duke.
     * @param i Integer to determine position of Imageview.
     * @return DialogBox with appropriate message and photo.
     */
    public static DialogBox getUserDialog(String fullCommand, ImageView iv, int i) {
        Label label = new Label(fullCommand);
        label.setWrapText(true);
        label.setFont(new Font("Arial", 12));
        label.setMaxSize(650, 800);
        DialogBox db = new DialogBox(label, iv);
        if (i == 1) {
            db.flip();
        }
        return db;
    }
}
