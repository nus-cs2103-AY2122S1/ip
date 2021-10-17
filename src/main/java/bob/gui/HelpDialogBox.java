package bob.gui;

import javafx.geometry.Pos;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

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
        link.setFont(Font.font("Comic Sans MS", FontWeight.NORMAL, FontPosture.REGULAR, 13));

        displayPicture.setFitWidth(150.0);
        displayPicture.setFitHeight(150.0);
        Circle circle = new Circle(50);
        circle.setCenterX(displayPicture.getFitWidth() / 2);
        circle.setCenterY(displayPicture.getFitHeight() / 2);
        displayPicture.setClip(circle);

        this.setAlignment(Pos.CENTER_LEFT);
        this.getChildren().addAll(displayPicture, link);
        this.setStyle("-fx-border-color: AZURE; -fx-border-width: 2;"
                + "-fx-border-radius: 30; -fx-border-insets: 5;");
    }
}
