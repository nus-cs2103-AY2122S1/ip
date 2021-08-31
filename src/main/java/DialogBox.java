import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;
    private final Insets PHOTO_MARGIN = new Insets(10, 10, 10, 10);
    private final Insets TEXT_MARGIN = new Insets(15, 15, 15, 15);
    private final Insets DIALOG_MARGIN = new Insets(5, 5, 5, 5);
    private final CornerRadii BACKGROUND_CORNER_RADII = new CornerRadii(10.0);
    private final Color BACKGROUND_COLOR = Color.rgb(210, 212, 253);
    private final BackgroundFill BACKGROUND_FILL = new BackgroundFill(BACKGROUND_COLOR,
            BACKGROUND_CORNER_RADII, DIALOG_MARGIN);

    public DialogBox(String string, Image image) {
        text = new Label(string);
        displayPicture = new ImageView(image);

        text.setWrapText(true);
        text.setPrefWidth(300.0);
        displayPicture.setFitWidth(75.0);
        displayPicture.setFitHeight(75.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
        this.setMargin(displayPicture, PHOTO_MARGIN);
        this.setMargin(text, TEXT_MARGIN);

        Background background = new Background(BACKGROUND_FILL);
        this.setBackground(background);
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

    public static DialogBox getUserDialog(String string, Image image) {
        return new DialogBox(string, image);
    }

    public static DialogBox getDukeDialog(String string, Image image) {
        var db = new DialogBox(string, image);
        db.flip();
        return db;
    }
}