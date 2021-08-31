package duke;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;

public class Controller {
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private TextArea display;
    @FXML
    private ScrollPane scrollPane;

    private String command;
    private MyList list;
    private Storage storage;
    private Image icon = new Image("https://static.zerochan.net/Shinomiya.Kaguya.full.2917139.png");

    public Controller() {
        this.list = new MyList();
        this.storage = new Storage(this.list, "./Data.txt");
        storage.load();
    }

    public void enter(KeyEvent e){
        System.out.println("HEllo");
        if (e.getCode().equals(KeyCode.ENTER)) {
            System.out.println("ENTER!");
            handleUserInput(new ActionEvent());
        }
    }

    public void handleUserInput(ActionEvent e) {

        this.command = this.userInput.getText();
        this.userInput.setText("");
        if (!this.command.equals("")) {
            dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
            this.dialogContainer.getChildren().addAll(
                    new DialogBox(getUserDialog(), new ImageView(icon), true),
                    new DialogBox(getDukeResponse(), new ImageView(icon), false)
            );
        }
    }

    public Label getUserDialog() {
        Label textToAdd = new Label(this.command);
        return textToAdd;
    }

    public Label getDukeResponse() {
        Parser p = new Parser(this.list, this.storage);
        String response = p.dukeResponse(this.command);
        Label textToAdd = new Label(response);
        return textToAdd;

    }

}
