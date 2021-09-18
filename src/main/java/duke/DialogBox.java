package duke;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.text.Font;
import javafx.scene.shape.Circle;

public class DialogBox extends HBox {

    @FXML
    private Label text = new Label();
    @FXML
    private ImageView displayPicture = new ImageView();


    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.text.setText(text);
        this.displayPicture.setImage(img);

        this.text.setPadding(new Insets(8.0));
        this.text.setTextFill(Color.SLATEGREY);
        this.text.setFont( new Font(16) );
        this.text.setWrapText(true);

        Circle clip = new Circle(50);
        this.displayPicture.setFitWidth(100.0);
        this.displayPicture.setFitHeight(100.0);
        clip.setCenterX(displayPicture.getFitWidth() / 2);
        clip.setCenterY(displayPicture.getFitHeight() / 2);
        this.displayPicture.setClip(clip);

        this.setPadding(new Insets(10.0));
        this.setAlignment(Pos.CENTER_RIGHT);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        this.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, new CornerRadii(1), new Insets(1))));
        this.setAlignment(Pos.CENTER_LEFT);
    }
    
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
