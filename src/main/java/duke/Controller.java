package duke;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.File;
import java.net.URL;

public class Controller {
    @FXML
    private Button addBth;
    @FXML
    private Button deleteBtn;
    @FXML
    private Button listAllBtn;
    @FXML
    private Button markBtn;
    @FXML
    private Button sendBtn;
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
            this.dialogContainer.getChildren().addAll(getUserDialog(), getDukeResponse());
        }


    }

    public Label getUserDialog() {
        Label textToAdd = new Label(this.command);
        textToAdd.setFont(new Font(15));
        textToAdd.setWrapText(true);
        textToAdd.setPadding(new Insets(10));
        return textToAdd;
    }

    public Label getDukeResponse() {
        Parser p = new Parser(this.list, this.storage);
        String response = p.dukeResponse(this.command);
        Label textToAdd = new Label(response);
        textToAdd.setWrapText(true);
        textToAdd.setFont(new Font(15));
        textToAdd.setPadding(new Insets(10));
        return textToAdd;

    }

}
