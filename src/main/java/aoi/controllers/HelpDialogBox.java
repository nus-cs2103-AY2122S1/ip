package aoi.controllers;

import java.io.IOException;
import java.util.Collections;

import aoi.Main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

/**
 * An example of a custom control using FXML.
 * This control represents a help dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class HelpDialogBox extends HBox {
    @FXML
    private Hyperlink hyperlink;
    @FXML
    private ImageView displayPicture;

    private HelpDialogBox(Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/HelpDialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        hyperlink.getStyleClass().add("help-link");
        hyperlink.setText("https://mslevis.github.io/ip/");
        hyperlink.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                new Main().getHostServices().showDocument(hyperlink.getText());
            }
        });
        displayPicture.setImage(img);
        displayPicture.setClip(new Circle(50, 40, 40));
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

    public static HelpDialogBox getHelpDialog(Image img) {
        var db = new HelpDialogBox(img);
        db.flip();
        return db;
    }
}
