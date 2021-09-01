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
 * Class to create dialog box with user photo and message
 */
public class DialogBox extends HBox {
    private Label text;
    private ImageView displayPicture;

    /**
     * Method to init Dialog box
     * @param l
     * @param iv
     */
    public DialogBox(Label l, ImageView iv) {
        text = l;
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
     * Method that returns a dialog box with appropriate message and photo
     * @param fullCommand Message
     * @param iv Image of user/duke
     * @param i To determine layout of Dialog box
     * @return dialog box with appropriate message and photo
     */
    public static DialogBox getUserDialog(String fullCommand, ImageView iv, int i) {
        Label l = new Label(fullCommand);
        l.setWrapText(true);
        l.setFont(new Font("Arial", 12));
        l.setMaxSize(650, 800);
        DialogBox db = new DialogBox(l, iv);
        if (i == 1) {
            db.flip();
        }
        return db;
    }
}
