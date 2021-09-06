package bob.gui;

import javafx.geometry.Pos;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class HelpDialogBox extends HBox {

    /** Help message sent in the chat */
    private Hyperlink link;

    /** Image of message sender */
    private ImageView displayPicture;

    /**
     * Constructor for a DialogBox instance.
     *
     * @param l Hyperlink object containing the link to the help page.
     * @param iv Image of entity that is "speaking" the dialog.
     */
    public HelpDialogBox(Hyperlink l, ImageView iv) {
        link = l;
        displayPicture = iv;

        link.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        this.setAlignment(Pos.CENTER_LEFT);
        this.getChildren().addAll(displayPicture, link);

        /**ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);*/
    }
}
