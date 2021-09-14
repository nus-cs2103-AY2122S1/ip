package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextFlow;

/**
 * Class that represent a dialog made in the GUI.
 *
 * @author Aiken Wong
 */
public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;
    private TextFlow textFlow = new TextFlow();

    /**
     * Instantiates a DialogBox object.
     *
     * @param label The given Label object.
     * @param imageView The given imageView object
     */
    public DialogBox(Label label, ImageView imageView) {
        text = label;
        displayPicture = imageView;
        text.setWrapText(true);
        text.setMaxWidth(250);
        textFlow.getChildren().add(text);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);
        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(textFlow, displayPicture);
    }


    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);

        this.getChildren().setAll(tmp);

    }

    /**
     * Constructor for a new user DialogBox.
     *
     * @param label The given Label object.
     * @param imageView The given imageView object
     * @return
     */
    public static DialogBox getUserDialog(Label label, ImageView imageView) {
        return new DialogBox(label, imageView);
    }

    /**
     * Constructor for a new Duke DialogBox.
     *
     * @param label The given Label object.
     * @param imageView The given imageView object
     * @return
     */
    public static DialogBox getDukeDialog(Label label, ImageView imageView) {
        DialogBox db = new DialogBox(label, imageView);
        db.flip();
        return db;
    }



}
