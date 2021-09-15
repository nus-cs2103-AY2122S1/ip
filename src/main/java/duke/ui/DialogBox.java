package duke.ui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

// @@author kevinmingtarja-reused
// Reused from https://se-education.org/guides/tutorials/javaFxPart4.html
// with minor modifications
public class DialogBox extends HBox {
    private static Integer counter = 0;
    private final String USER_BG_COLOR = "#06f";
    private final String BOT_BG_COLOR = "373737";
    private final String ERROR_BG_COLOR = "D4000E";
    private final Integer CORNER_RADIUS = 10;
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        dialog.setPadding(new Insets(4));
        dialog.setTextFill(Color.WHITE);
        // Prevents long text from being cut off
        dialog.setMinHeight(Region.USE_PREF_SIZE);
        dialog.setTranslateY(16);
        dialog.setTranslateX(-8);

        if (isError(text)) {
            dialog.setBackground(new Background(new BackgroundFill(Color.web(ERROR_BG_COLOR),
                    new CornerRadii(CORNER_RADIUS), new Insets(-4))));
        } else if (counter % 2 == 0) {
            dialog.setBackground(new Background(new BackgroundFill(Color.web(BOT_BG_COLOR),
                    new CornerRadii(CORNER_RADIUS), new Insets(-4))));
        } else {
            dialog.setBackground(new Background(new BackgroundFill(Color.web(USER_BG_COLOR),
                    new CornerRadii(CORNER_RADIUS), new Insets(-4))));
        }
        counter++;

        displayPicture.setScaleX(0.75);
        displayPicture.setScaleY(0.75);
        displayPicture.setClip(new Circle(40, 40, 40));
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }

    private boolean isError(String text) {
        return text.startsWith("Error: Whoops...");
    }
}