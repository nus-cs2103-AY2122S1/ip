package duke;

import java.io.IOException;

import duke.command.Command;
import duke.exception.DukeException;
import duke.task.TaskList;
import javafx.application.Application;
import javafx.collections.FXCollections;
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
import javafx.stage.Stage;


/**
 * Duke is a educational software project designed to take you through
 * the steps of building a small software incrementally, while applying
 * as many Java and SE techniques as possible along the way.
 */
public class Duke extends Application {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.jpg"));

    public Duke() {
    }

    /**
     * Constructs a Duke with the specified filePath.
     *
     * @param filePath Path of the file to store the task list.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load(false));
        } catch (DukeException | IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }


    /**
     * Runs the Duke instance.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        // set up all the elements
        // will show when overflow (or based on the policy)
        scrollPane = new ScrollPane();
        // to contain the chat box
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        // user input element
        userInput = new TextField();
        // send button element
        sendButton = new Button("Send");

        // main layout: anchor pane (can be used to set margin)
        AnchorPane mainLayout = new AnchorPane();
        // add elements to layout
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        // scene: drawing board
        scene = new Scene(mainLayout);

        // stage: application window (will only show the scene)
        stage.setScene(scene);
        // show the stage
        stage.show();

        // title: at the top of the window
        stage.setTitle("Duke");
        // window cannot be resized
        stage.setResizable(false);
        // set min height of window
        stage.setMinHeight(600.0);
        // set min width
        stage.setMinWidth(400.0);

        // set size of layout
        mainLayout.setPrefSize(400.0, 600.0);

        // set size of scroll pane:
        scrollPane.setPrefSize(385, 535);
        // never show horiz scroll bar
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        // always who vert scroll bar
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        // scroll position (0 to 1) (used to programmatically scroll the pane)
        scrollPane.setVvalue(1.0);
        // fit scroll pane to width
        scrollPane.setFitToWidth(true);

        // reset back to intrinsic size (-1)
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        // user input: set preferred width (reduce size from right) (fixed at left)
        userInput.setPrefWidth(325.0);

        // set button width
        sendButton.setPrefWidth(55.0);

        // set the margin
        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);


        // button on click:
        sendButton.setOnMouseClicked((event) -> {
            // add text element to the dialogcontainer (vbox)
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            // then clear user input (text field)
            userInput.clear();
        });

        // on enter, add to the dialog box as well
        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        // listen to height prop, if change scroll to the bottom
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));


        // button on click, handle user input
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        // button on enter, handle user input
        userInput.setOnAction((event) -> {
            handleUserInput();
        });
        // more code to be added here later
    }

    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        // wrap text if exceed length of the container?
        textToAdd.setWrapText(true);


        // return as label element (node)
        return textToAdd;
    }

    // private void handleUserInput() {
    //     // get label element
    //     Label userText = new Label(userInput.getText());
    //     // get label element (text)
    //     Label dukeText = new Label(getResponse(userInput.getText()));
    //     // add to vbox (both user and duke)
    //     dialogContainer.getChildren().addAll(
    //         new DialogBox(userText, new ImageView(user)),
    //         new DialogBox(dukeText, new ImageView(duke))
    //     );
    //     // clear the input
    //     userInput.clear();
    // }

    private String getResponse(String input) {
        return "Duke heard: " + input;
    }

    private void handleUserInput() {
        // get label elements
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        // add to dialog box (dialog elements)
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(userText, new ImageView(user)),
            DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }


}
