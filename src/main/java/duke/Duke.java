package duke;

import command.Command;
import exception.DukeException;
import exception.DukeExceptionType;

import java.io.InputStream;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Represents the main Duke program. Running this class's main function executes the program.
 */
public class Duke extends Application {

    private final Ui ui;
    private final TaskList taskList;

    private ScrollPane scrollPane;
    private TextField userInput;

    /**
     * Default constructor for the Duke program.
     * Uses filepath data/duke.txt by default.
     */
    public Duke() {
        ui = new Ui();
        Storage storage = new Storage("data", "duke.txt");
        taskList = new TaskList(storage);
    }

    /**
     * Constructor for the Duke program with custom filepath.
     *
     * @param fileDirectory Directory of the hard disk.
     * @param fileName The hard disk.
     */
    public Duke(String fileDirectory, String fileName) {
        ui = new Ui();
        Storage storage = new Storage(fileDirectory, fileName);
        taskList = new TaskList(storage);
    }

    /**
     * Runs the main logic of the Duke program.
     *
     * @param stream The desired stream for the program's input.
     */
    public void run(InputStream stream) {
        // Set up Scanner for input and isBye boolean for while loop
        Scanner input = new Scanner(stream);
        boolean isBye = false;

        // Welcome message
        ui.showWelcome();

        // Continuously takes user input until "bye" command is given
        while (!isBye) {
            try {
                // performing action based on user command
                ui.showInputPrompt();
                ui.readCommand(input);
                String fullCommand = userInput.getText();
                ui.showLine();
                Command c = Parser.parse(fullCommand, taskList);
                c.execute(taskList);
                isBye = c.isBye();

            } catch (DukeException e) {
                ui.showException(e);

            } catch (NumberFormatException e) {
                ui.showException(new DukeException(DukeExceptionType.INVALID_TASK_INDEX));

            } catch (DateTimeParseException e) {
                ui.showException(new DukeException(DukeExceptionType.INVALID_DATETIME));

            } finally {
                ui.showLine();
            }
        }

        input.close();
    }

    @Override
    public void start(Stage stage) {
        scrollPane = new ScrollPane();
        VBox dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        Button sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        Scene scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

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

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        try {
            Command c = Parser.parse(input, taskList);
            return c.execute(taskList);

        } catch (DukeException e) {
            return ui.showException(e);

        } catch (NumberFormatException e) {
            return ui.showException(new DukeException(DukeExceptionType.INVALID_TASK_INDEX));

        } catch (DateTimeParseException e) {
            return ui.showException(new DukeException(DukeExceptionType.INVALID_DATETIME));
        }
    }

    String showWelcome() {
        return ui.showWelcome();
    }

    public static void main(String[] args) {
        new Duke().run(System.in);
    }
}
