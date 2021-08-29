package duke;

import java.time.format.DateTimeParseException;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidCommandException;
import duke.exceptions.InvalidTaskNumberException;
import duke.exceptions.MissingDeadlineException;
import duke.exceptions.MissingEventTimeException;
import duke.exceptions.MissingTaskNameException;
import duke.exceptions.MissingTaskNumberException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Duke is a Personal Assistant Chatbot that keeps track of various tasks.
 *
 * @author Adam Oh Zhi Hong
 */
public class Duke extends Application {
    /** A class to keep track of all tasks of the Duke instance **/
    private TaskList taskList = new TaskList("data/duke.txt");

    private TextField userInput;
    private VBox dialogContainer;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    /**
     * Starts an instance of Duke
     */
    @Override
    public void start(Stage stage) {
        boolean running = true;
        // Setting up components
        ScrollPane scrollPane = new ScrollPane();
        VBox dialogContainer = new VBox();
        this.dialogContainer = dialogContainer;
        scrollPane.setContent(dialogContainer);

        TextField userInput = new TextField();
        this.userInput = userInput;
        Button sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        Scene scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();

        // Formatting the stage
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(400, 540);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325);

        sendButton.setPrefWidth(65);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 2.0);
        AnchorPane.setRightAnchor(sendButton, 2.0);

        AnchorPane.setLeftAnchor(userInput , 2.0);
        AnchorPane.setBottomAnchor(userInput, 2.0);

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
//        while (running) {
//            // Get input from the user
//            String command = UI.readCommand();
//            try {
//                Parser.handle(command, taskList);
//                running = Parser.isRunning();
//            } catch (InvalidCommandException e) {
//                System.out.println("I'm afraid I don't recognise that, please try again!");
//            } catch (MissingTaskNameException e) {
//                System.out.println("Duke.Task name cannot be empty!");
//            } catch (MissingTaskNumberException e) {
//                System.out.println("Did you forget to enter your task number?");
//            } catch (InvalidTaskNumberException e) {
//                System.out.println("Sorry, that task does not exist!");
//            } catch (MissingDeadlineException e) {
//                System.out.println("When is that due? Let me know after '/by'!");
//            } catch (MissingEventTimeException e) {
//                System.out.println("When is the event happening? Let me know after '/at'!");
//            } catch (DateTimeParseException e) {
//                System.out.println("Oops, did you enter your date in yyyy-mm-dd format?");
//            } catch (DukeException e) {
//                UI.dukeException();
//            }
//        }
    }

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
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        return "Duke heard: " + input;
    }
}

