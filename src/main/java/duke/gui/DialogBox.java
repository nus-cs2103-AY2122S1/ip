package duke.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class DialogBox extends HBox {
    private Label text;

    /**
     * Constructor for creating a dialogue box to be displayed to the user.
     *
     * @param l The string that is to be displayed in the GUI to the user
     */
    public DialogBox(Label l) {
        this.text = l;

        text.setWrapText(true);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Return the DialogBox corresponding to Duke's respond.
     *
     * @param s The String inputted by the user
     * @return The DialogBox with the user input
     */
    public static DialogBox getUserDialog(String s) {
        return new DialogBox(new Label(s));
    }

    /**
     * Return the DialogBox corresponding to Duke's respond. Duke's DialogBox is flipped
     * that of the user's DialogBox.
     *
     * @param s The String returned from Duke that is to be displayed to the user
     * @return The DialogBox with Duke's respond
     */
    public static DialogBox getDukeDialog(String s) {
        DialogBox db = new DialogBox(new Label(s));
        db.flip();
        return db;
    }

    /**
     * Return the DialogBox corresponding to Duke's respond. Duke's DialogBox is flipped
     * that of the user's DialogBox.
     *
     * @param s The String returned from Duke that is to be displayed to the user
     * @return The DialogBox with Duke's respond
     */
    public static DialogBox getErrorDialog(String s) {
        Label l = new Label(s);
        l.setTextFill(Color.RED);

        DialogBox db = new DialogBox(l);
        db.flip();
        return db;
    }
}
