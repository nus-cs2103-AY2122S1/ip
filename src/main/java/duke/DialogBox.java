package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    /**
     * @param l
     * @param iv
     */
    public DialogBox(Label l, ImageView iv) {
        // each row in vbox
        text = l;
        displayPicture = iv;

        // wrap the text (if exceed)
        text.setWrapText(true);
        // set fit width (of the image)
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        // align this row to the top right (everything inside will align to top right) (top and right)
        this.setAlignment(Pos.TOP_RIGHT);
        // add all elements to the row
        this.getChildren().addAll(text, displayPicture);
    }

    public static DialogBox getUserDialog(Label l, ImageView iv) {
        // align to right
        return new DialogBox(l, iv);
    }

    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        // align to left
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        // reverse the collections (list of nodes) (ie. FX collections)
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        // set all children again
        this.getChildren().setAll(tmp);
    }


}
