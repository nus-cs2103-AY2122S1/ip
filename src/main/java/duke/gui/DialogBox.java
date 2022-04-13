package duke.gui;

import java.io.IOException;

import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

/**
 * The Dialog Box. Has DukeDialogBox and UserDialogBox as it's children.
 */
public class DialogBox extends HBox {
    @FXML
    private ImageView displayPicture;

    /**
     * Acts as the constructor for DialogBox.
     *
     * @param fxmlResourceDir The location of the FXML file.
     * @param img The Image file to display.
     */
    protected DialogBox(String fxmlResourceDir, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource(fxmlResourceDir));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        displayPicture.setImage(img);
        this.cropToCircle();
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    protected void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Crops the displayPicture into a circle.
     */
    private void cropToCircle() {
        Circle circle = new Circle(displayPicture.getFitWidth() / 2, displayPicture.getFitHeight() / 2, 20);
        displayPicture.setClip(circle);
    }
}
