package duke;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;
    private boolean isUser;

    /**
     * Constructor for the DialogBox.
     * @param l The label containing the user message or Duke's reply.
     * @param iv The profile image of the user or Duke
     * @param isUser True if the DialogBox belongs to the user and false otherwise.
     */
    public DialogBox(Label l, ImageView iv, boolean isUser) {
        this.text = l;
        this.displayPicture = iv;
        this.isUser = isUser;

        this.setStyle("-fx-border-color: black;");

        this.text.setWrapText(true);
        this.text.setFont(new Font(15));
        this.text.setPadding(new Insets(10));
        this.text.setPrefWidth(270);

        this.displayPicture.setFitWidth(100.0);
        this.displayPicture.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.setPadding(new Insets(10));

        if (isUser) {
            this.getChildren().addAll(displayPicture, text);
        } else {
            this.getChildren().addAll(text, displayPicture);
        }
    }

}
