package duke;

import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Represents a chatbot that can be run with functionality of a to-do list keeper.
 * Each <code>Duke</code> object has a <code>Storage</code> to load and save a .txt list,
 * <code>TaskList</code> for storing the <code>Task</code>s, and a <code>Ui</code> for interacting
 * with user inputs.
 */
public class Duke extends Application {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Returns a Duke object.
     */
    public Duke() {
        ui = new Ui(this);
        storage = new Storage("data/list.txt", this);
        tasks = new TaskList(this);
    }

    public TaskList getTasks() {
        return this.tasks;
    }
    public Storage getStorage() {
        return this.storage;
    }
    public Ui getUi() {
        return this.ui;
    }

    /**
     * Runs the duke chatbot by loading the TaskList and calling the Ui and Parser
     */
    private void run() {
        System.out.println(ui.showWelcomeMessage());
        try {
            System.out.println(storage.loadFileToList());
        } catch (FileNotFoundException e) {
            System.out.println(ui.showLoadingError());
        } finally {
            while (true) {
                String command = ui.getUserCommand();
                Parser parser = new Parser(this);
                String response = parser.handleInput(command);
                System.out.println(response);
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    private String loadListGui() {
        try {
            return storage.loadFileToList();
        } catch (FileNotFoundException e) {
            return ui.showLoadingError();
        }
    }

    private String parseGui(String command) {
        Parser parser = new Parser(this);
        String response = parser.handleInput(command);
        return response;
    }

    @Override
    public void start(Stage stage) {

        String list = loadListGui();

        Label welcome = new Label(ui.showWelcomeMessage());
        Label label = new Label("Enter your command here!");
        TextField inputField = new TextField();
        Label output = new Label(list);
        Button button1 = new Button("Enter");
        Button button2 = new Button("Clear");

        // Action event
        inputField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER ) {
                output.setText(parseGui(inputField.getText()));
            } else if (event.getCode() == KeyCode.ESCAPE ) {
                output.setText("Output cleared.\nPlease enter a new command");
                inputField.setText("");
            }
        });
        EventHandler<ActionEvent> parse = event -> {
            output.setText(parseGui(inputField.getText()));
        };
        EventHandler<ActionEvent> clear = event -> {
            output.setText("Output cleared.\nPlease enter a new command");
            inputField.setText("");
        };

        // When button is pressed
        button1.setOnAction(parse);
        button2.setOnAction(clear);

        // Creating a Grid Pane
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(800, 750);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);

        // Arranging all the nodes in the grid
        gridPane.add(welcome, 1, 0);
        gridPane.add(label, 1, 2);
        gridPane.add(inputField, 1, 3);
        gridPane.add(button1, 1, 7);
        gridPane.add(button2, 1, 10);
        gridPane.add(output, 1, 14);

        // Styling nodes
        button1.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white; ");
        button1.setFont(Font.font("Calibri"));
        button2.setStyle("-fx-background-color: grey; -fx-text-fill: white; ");
        button2.setFont(Font.font("Calibri"));
        welcome.setStyle("-fx-font: normal 16px 'robota' ");
        label.setStyle("-fx-font: normal bold 20px 'calibri' ");
        gridPane.setStyle("-fx-background-color: rgb(235, 235, 255);");
        output.setStyle("-fx-font: normal 16px 'robota' ");

        // Creating a scene object
        Scene scene = new Scene(gridPane);
        // Setting title to the Stage
        stage.setTitle("Friend - Keep Track of Tasks!");
        // Adding scene to the stage
        stage.setScene(scene);
        // Displaying the contents of the stage
        stage.show();
    }
}
