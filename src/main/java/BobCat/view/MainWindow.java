package BobCat.view;

import BobCat.BobCat;
import BobCat.exception.BobCatException;
import BobCat.executor.ExecutionUnit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane; // This will find the appropriate fxml id
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private final Image userImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaUser.png")));
    private final Image bobcatImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaBobCat.png")));

    private ExecutionUnit executor;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setExecutor(ExecutionUnit d) {
        executor = d;
    }

    @FXML
    private void handleUserInput(ActionEvent actionEvent) {
        String query = userInput.getText();
        respondUser(new String[]{query});

        try {
            respondBobCat(executor.executeCommand(query));
        } catch (BobCatException e) {
            respondError(e);
        } catch (IOException e) {
            respondError(new BobCatException(e.getMessage()));
        }
        userInput.clear();
    }

    public void respondBobCat(String[] reply) {
        display(reply, (text) -> DialogBox.getDukeDialog(getDialogLabel(text), new ImageView(bobcatImage)));
    }

    public void respondUser(String[] reply) {
        display(reply, (text) -> DialogBox.getUserDialog(getDialogLabel(text), new ImageView(userImage)));
    }

    public void respondError(BobCatException error) {
        respondBobCat(new String[]{"☹ OOPS!!! ", error.getMessage()});
    }

    @FunctionalInterface
    private interface TextToDialogBox {
        DialogBox transform(String text);
    }

    private void display(String text, TextToDialogBox textTransform) {
        dialogContainer.getChildren().add(textTransform.transform(text));
    }

    private void display(String[] reply, TextToDialogBox textTransform) {
        String result = Arrays.stream(reply).reduce("", (x, y) -> x + "\n" + y);
        display(result, textTransform);
    }

    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }
}
