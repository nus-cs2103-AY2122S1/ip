package duke.ui.controllers;

import java.io.IOException;
import java.util.Collections;
import java.util.Objects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class DialogBox extends HBox {
    @FXML
    private Text dialog;
    @FXML
    private ImageView displayPicture;

    private static final String USER_IMAGE_NAME = "user.png";
    private static final String DUKE_IMAGE_NAME = "duke.png";

    private DialogBox(String text, Image img, boolean flipped) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);

        if (flipped) {
            ObservableList<Node> children = FXCollections.observableArrayList(getChildren());
            Collections.reverse(children);
            getChildren().setAll(children);
            setAlignment(Pos.TOP_LEFT);
        }
    }

    public static DialogBox ofUser(String message) {
        return new DialogBox(message, loadImage(USER_IMAGE_NAME), false);
    }

    public static DialogBox ofDuke(String message) {
        return new DialogBox(message, loadImage(DUKE_IMAGE_NAME), true);
    }

    private static Image loadImage(String name) {
        return new Image(Objects.requireNonNull(DialogBox.class.getResourceAsStream("/images/" + name)));
    }
}
