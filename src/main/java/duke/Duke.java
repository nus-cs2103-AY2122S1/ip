package duke;

import duke.command.ByeCommand;
import duke.command.Command;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;

/**
 * This program duke.Duke is a chatbot.
 *
 * @author: Toh Bing Cheng
 * @version: CS2103T AY21 Semester 1
 */
public class Duke extends javafx.application.Application {

    private Ui ui;
    private TaskList taskList;
    private Storage storage;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private boolean firstTime = true;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Duke constructor that requires the file path.
     *
     * @param filePath String of the filepath.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (InitialisationError e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }

    }

    public Duke() {}

    public static void main(String[] args) {
        new Duke("data/dukeData.json").runProgram();
    }

    /**
     * This method runs the program indefinitely till user types in "bye".
     */
    public void runProgram() {
        System.out.println(ui.getWelcome());
        boolean run = true;

        while (run) {
            try {
                // wait to read in the user's input
                String input = ui.readCommand();
                Command userCommand = Parser.parse(input);
                System.out.println(userCommand.execute(taskList, ui, storage));
                if (userCommand instanceof ByeCommand) {
                    run = false;
                }
            } catch (IncompleteCommandException e) {
                run = true;
                System.out.println(ui.printErrorMessage(e.getMessage()));
            } catch (Exception e) {
                run = false;
                e.printStackTrace();
            }
            if (run) {
                System.out.println(ui.showLoopWelcome());
            }

        }
    }


    @Override
    public void start(Stage stage) throws Exception {
        //Step 1. Setting up required components
        assert stage != null;
        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

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

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        assert userInput != null;
        assert dialogContainer != null;
        String userText = userInput.getText();
        String dukeText = getResponse(userInput.getText());
        DialogBox userDb = DialogBox.getUserDialog(userText, user);
        DialogBox dukeDb = DialogBox.getDukeDialog(dukeText, duke);
        dialogContainer.setPadding(new Insets(100,100,100,100));
        dialogContainer.getChildren().addAll(
                userDb,
                dukeDb
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        String output = "";
        try {
            assert input != null;
            Command userCommand = Parser.parse(input);
            output = userCommand.execute(taskList, ui, storage);

            boolean userStillUsing = !(userCommand instanceof ByeCommand);
            if (userStillUsing) {
                output += "\n" + ui.showLoopWelcome();
            }
        } catch (IncompleteCommandException e) {
            output = ui.printErrorMessage(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            output = e.getMessage();
        }

        return "Duke: " + output;
    }


}
