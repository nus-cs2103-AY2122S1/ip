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

    public static DialogBox getUserDialog(Label l) {
        return new DialogBox(l);
    }

    public static DialogBox getDukeDialog(Label l) {
        var db = new DialogBox(l);
        db.flip();
        return db;
    }
}
//@@author
