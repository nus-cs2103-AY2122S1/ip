package BobCat.view;

import BobCat.exception.BobCatException;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.Arrays;

public class GraphicalUi{
    private final VBox dialogContainer;
    private final Image user;
    private final Image bobcat;

    @FunctionalInterface
    public interface TextToDialogBox {
        DialogBox transform(String text);
    }

    public GraphicalUi(VBox dialogContainer, Image user, Image bobcat) {
        this.dialogContainer = dialogContainer;
        this.user = user;
        this.bobcat = bobcat;
    }

    private void display(String text, TextToDialogBox textTransform) {
        dialogContainer.getChildren().add(textTransform.transform(text));
    }

    private void display(String[] reply, TextToDialogBox textTransform) {
        String result = Arrays.stream(reply).reduce("", (x, y) -> x + "\n" + y);
        display(result, textTransform);
    }

    public void respondBobCat(String[] reply) {
        display(reply, (text) -> DialogBox.getDukeDialog(getDialogLabel(text), new ImageView(bobcat)));
    }

    public void respondUser(String[] reply) {
        display(reply, (text) -> DialogBox.getUserDialog(getDialogLabel(text), new ImageView(user)));
    }

    public void respondError(BobCatException error) {
        respondBobCat(new String[]{"☹ OOPS!!! ", error.getMessage()});
    }

    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }
}
