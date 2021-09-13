package duke.utils;

import duke.commands.Command;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javafx.event.EventHandler;
import java.util.Scanner;

/**
 * Class that handles taking in user input
 */
public class Ui {

    private static final String LOGO =
            " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String INTRO = "Hello from\n" + LOGO + "\n" + "What can I do for you?";
    private static final String ENDING = "Bye. Hope to see you again soon!\n";

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));


    private Storage storage;
    private TaskList tasklist;
    private Parser parser;

    private Scanner scanner;

    private Stage stage;
    private ScrollPane scrollPane;
    private VBox vbox;
    private TextField textField;
    private Label label;
    private StringBuilder stringBuilder;
    private Button sendButton;
    private AnchorPane mainLayout;


    /**
     * Constructor that initializes a Ui object
     */
    public Ui(Stage stage, TaskList taskList, Storage storage, Parser parser) {
        scanner = new Scanner(System.in);
        this.stage = stage;
        this.tasklist = taskList;
        this.storage = storage;
        this.parser = parser;
        this.scrollPane = new ScrollPane();
        this.vbox = new VBox();
        this.textField = new TextField();
        this.label = new Label();
        this.stringBuilder = new StringBuilder();
        this.sendButton = new Button("Send");
        this.mainLayout = new AnchorPane();


        label.setWrapText(true);
        vbox.setAlignment(Pos.TOP_LEFT);
        vbox.getChildren().addAll(textField, label);

        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setContent(vbox);
    }

    /**
     * Initializes the UI by displaying the stage of the javafx application.
     */
    public void init() {
        stage.setTitle("Mr Duke's Todo App");
        Color bgColor = Color.AZURE;

        mainLayout.getChildren().addAll(scrollPane, textField, sendButton);

        Scene scene = new Scene(mainLayout, bgColor);

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage
        start();

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        vbox.setPrefHeight(Region.USE_COMPUTED_SIZE);

        textField.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(textField, 1.0);
        AnchorPane.setBottomAnchor(textField, 1.0);

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

//        textField.setOnAction((event) -> {
//            handleUserInput();
//        });

        //Scroll down to the end every time dialogContainer's height changes.
        vbox.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }


    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(textField.getText());
        vbox.getChildren().addAll(DialogBox.getUserDialog(userText, new ImageView(user)));
        Label dukeText = new Label(getResponse(textField.getText()));
        vbox.getChildren().addAll(DialogBox.getDukeDialog(dukeText, new ImageView(duke)));
        textField.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        String userInput = textField.getText();
        stringBuilder.append(userInput);
        label.setText(stringBuilder.toString());

        try {
            //showLine(); // show the divider line ("_______")
            Command c = parser.parseInput(userInput);
            c.execute(tasklist, Ui.this, storage);
        } catch (Exception ex) {
            showError(ex);
        } finally {
            showLine();
        }

        //showLine();
        return "";
    }

    public void printResponse(String message) {
        vbox.getChildren().addAll(
                DialogBox.getDukeDialog(new Label(message)));


    }

    /**
     * Returns the intro statement when app first launches
     */
    public void start(){

        vbox.getChildren().addAll(
                DialogBox.getDukeDialog(new Label(INTRO), new ImageView(duke) ));
        showLine();
    }

    /**
     * returns the ending statement before app closes
     */
    public void end() {
        vbox.getChildren().addAll(
                DialogBox.getDukeDialog(new Label(ENDING), new ImageView(duke) ));
        showLine();
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            System.out.println("Unable to close");
//        }
        stage.close();
    }


    /**
     * Prints a dashed divider line
     */
    public void showLine(){
        vbox.getChildren().addAll(
                DialogBox.getDukeDialog(new Label("\n________________________________________________________ \n")));

    }

    /**
     * Prints out the string message of the given Exception.
     * @param e Exception
     */
    public void showError(Exception e){
        stringBuilder.append(e.toString());
        label.setText(stringBuilder.toString());
    }





}
