package duke;

import javafx.application.Application;
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

import java.io.EOFException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Main class of the Duke chat-bot. When the main class is run, it creates an
 * instance of Duke which allows text-based user interaction.
 */
public class Duke extends Application {
    private final Ui ui;
    private final Scanner sc = new Scanner(System.in);
    private final Storage storage;
    private TaskList tasks = new TaskList();
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.jpg"));

    /**
     * Class constructor for Duke.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("duke.txt");
    }

    /**
     * Point of entry through which Duke can be run.
     *
     * @param args The commandline arguments.
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Loads the save file and begins to accept user input.
     */
    public void run() {
        try {
            tasks = storage.readSave();
        } catch (EOFException e) {
            ui.showNewSave();
        } catch (IOException | ClassNotFoundException e) {
            ui.showReadSaveError();
        }
        ui.showIntro();
        outer:
        while (true) {
            String userEntry = sc.nextLine();
            Command command;
            try {
                command = Parser.parseUserInput(userEntry);
            } catch (DukeException e) {
                ui.print(e.toString());
                continue;
            }
            Task task;
            switch (command.getOperation()) {
            case "bye":
                ui.showOutro();
                try {
                    storage.writeSave(tasks);
                } catch (IOException e) {
                    ui.showWriteSaveError();
                }
                break outer;
            case "list":
                ui.showTasks(tasks);
                break;
            case "done":
                task = tasks.get(command.getIndex() - 1);
                task.setDone(true);
                ui.showDone(task);
                break;
            case "todo":
                task = new ToDo(command.getDescription());
                tasks.add(task);
                ui.showAdded(task, tasks.size());
                break;
            case "deadline":
                task = new Deadline(command.getDescription(), command.getTime());
                tasks.add(task);
                ui.showAdded(task, tasks.size());
                break;
            case "event":
                task = new Event(command.getDescription(), command.getTime());
                tasks.add(task);
                ui.showAdded(task, tasks.size());
                break;
            case "delete":
                task = tasks.get(command.getIndex() - 1);
                tasks.delete(command.getIndex() - 1);
                ui.showDeleted(task, tasks.size());
                break;
            case "find":
                TaskList filteredTasks = tasks.find(command.getDescription());
                ui.showMatches(filteredTasks);
                break;
            default:
                break;
            }
        }
    }

    @Override
    public void start(Stage stage) {
        // Code reused from Jeffry Lum (https://se-education.org/)
        //Step 1. Setting up required components

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
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        userInput.setPrefWidth(325.0);
        sendButton.setPrefWidth(55.0);
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    private String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
