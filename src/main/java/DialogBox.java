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
    private static final Color USER_BACKGROUND_COLOR = Color.rgb(210, 212, 253);
    private static final Color MORGAN_BACKGROUND_COLOR = Color.rgb(236, 221, 254);
    private static final CornerRadii DIALOG_CORNER_RADII = new CornerRadii(10.0);
    private static final Insets PHOTO_MARGIN = new Insets(10, 10, 10, 10);
    private static final Insets TEXT_MARGIN = new Insets(15, 15, 15, 15);
    private static final Insets DIALOG_MARGIN = new Insets(5, 5, 5, 5);

    private Label text;
    private ImageView displayPicture;

    /**
     * Constructor for DialogBox.
     */
    public DialogBox(String string, Image image) {
        text = new Label(string);
        displayPicture = new ImageView(image);

        text.setWrapText(true);
        text.setPrefWidth(300.0);
        displayPicture.setFitWidth(50.0);
        displayPicture.setFitHeight(50.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
        this.setMargin(displayPicture, PHOTO_MARGIN);
        this.setMargin(text, TEXT_MARGIN);
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
        DialogBox output = new DialogBox(string, image);
        BackgroundFill backgroundFill = new BackgroundFill(USER_BACKGROUND_COLOR,
                DIALOG_CORNER_RADII, DIALOG_MARGIN);
        Background background = new Background(backgroundFill);
        output.setBackground(background);
        return output;
    }

    public static DialogBox getMorganDialog(String string, Image image) {
        DialogBox output = new DialogBox(string, image);
        output.flip();
        BackgroundFill backgroundFill = new BackgroundFill(MORGAN_BACKGROUND_COLOR,
                DIALOG_CORNER_RADII, DIALOG_MARGIN);
        Background background = new Background(backgroundFill);
        output.setBackground(background);
        return output;
    }
}
