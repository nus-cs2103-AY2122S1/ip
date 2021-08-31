package lania;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

//@@author nguyiyang-reused
//Reused from https://se-education.org/guides/tutorials/javaFx.html
// with minor modifications
public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);
        displayPicture.setClip(new Circle(50, 50, 50));
        text.setPadding(new Insets(0, 10, 0, 10));

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
        this.setPadding(new Insets(10, 0, 10, 0));
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(Label l, ImageView iv) {
        DialogBox UserDialogBox = new DialogBox(l, iv);
        UserDialogBox.setDialogBoxBackgroundColor("#ff9191");
        return UserDialogBox;
    }

    public static DialogBox getLaniaDialog(Label l, ImageView iv) {
        DialogBox LaniaDialogBox = new DialogBox(l, iv);
        LaniaDialogBox.flip();
        LaniaDialogBox.setDialogBoxBackgroundColor("#ffe591");
        return LaniaDialogBox;
    }

    private void setDialogBoxBackgroundColor(String hexValue) {
        this.setBackground(new Background(new BackgroundFill(Paint.valueOf(hexValue), null, null)));
    }
}
//@@author
