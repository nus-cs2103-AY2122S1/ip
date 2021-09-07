package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

//@@author wanyu-l-reused
//Reused from https://se-education.org/guides/tutorials/javaFxPart3.html
// with minor modifications
public final class DialogBox extends HBox {

    private Label text;

    /**
     * Constructs a DialogBox object.
     *
     * @param l the input by user or output by user wrapped by Label
     */
    public DialogBox(Label ... l) {
        for (Label label : l) {
            label.setWrapText(true);
            this.setAlignment(Pos.TOP_RIGHT);
            this.getChildren().addAll(label);
        }
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Gets a dialog box representing the user in the conversation.
     * @param l message to be shown as user's query
     * @return a dialog box representing the user
     */
    public static DialogBox getUserDialog(Label l) {
        return new DialogBox(l);
    }

    /**
     * Gets a dialog box representing the Duke in the conversation.
     * @param l message to be shown as Duke's answer
     * @return a dialog box representing Duke
     */
    public static DialogBox getDukeDialog(Label l) {
        var db = new DialogBox(l);
        db.flip();
        return db;
    }
}
//@@author
