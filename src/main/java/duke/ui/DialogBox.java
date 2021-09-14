package duke.ui;

import java.io.IOException;

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
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Dialog Box for Duke, in customization
     * @param text txt
     * @param image txt
     */
    public DialogBox(String text, Image image) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class
                    .getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(text);
        dialog.setMinHeight(Region.USE_PREF_SIZE);
        displayPicture.setImage(image);
        Circle circle = new Circle(50, 50, 45);
        displayPicture.setClip(circle);
        this.setSpacing(10);
        this.setBackground(new Background(new BackgroundFill(Color.BURLYWOOD,
                new CornerRadii(20), new Insets(10, 10, 10, 10))));
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> temp = FXCollections
                .observableArrayList(this.getChildren());
        FXCollections.reverse(temp);
        this.getChildren().setAll(temp);
    }

    public static DialogBox getUserDialog(String text, Image image) {
        return new DialogBox(text, image);
    }

    public static DialogBox getDukeDialog(String text, Image image) {
        DialogBox db = new DialogBox(text, image);
        db.flip();
        return db;
    }
}
