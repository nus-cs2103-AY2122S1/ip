package duke.Ui;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

class DialogBox extends HBox {
    private Label text;
    private ImageView dp;

    private DialogBox(String str, Image img) {
        this.text = new Label(str);
        this.dp = new ImageView(img);

        this.text.setWrapText(true);
        this.dp.setFitWidth(99);
        this.dp.setFitHeight(99);
    }

    public static DialogBox UserDialogBox(String str, Image img) {
        DialogBox db = new DialogBox(str, img);
        db.setAlignment(Pos.TOP_RIGHT);
        db.getChildren().addAll(db.text, db.dp);
        return db;
    }

    public static DialogBox DukeDialogBox(String str, Image img) {
        DialogBox db = new DialogBox(str, img);
        db.setAlignment(Pos.TOP_LEFT);
        db.getChildren().addAll(db.dp, db.text);
        return db;
    }
}
