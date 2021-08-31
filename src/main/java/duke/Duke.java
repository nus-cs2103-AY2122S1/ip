package duke;

import command.Command;
import duke.exception.DukeException;
import duke.gui.DialogBox;
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

public class Duke extends Application {
    /** Duke's Storage. Deals with loading tasks from the file and saving tasks in the file. **/
    private final Storage storage;

    /** Duke's TaskList. Containing the data structure for storing tasks. **/
    private TaskList taskList;

    /** Duke's user interface. Deals with interactions with the user. **/
    private final Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/lehao.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/zixuan.jpg"));
    /**
     * A public constructor to initialized Duke.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/taskList.txt");
        try {
            taskList = new TaskList(storage.loadTaskList());
        } catch (DukeException e) {
            ui.showDukeException(e);
            taskList = new TaskList();
        }
    }
    /**
     * A public constructor to initialized Duke.
     *
     * @param filePath The given filePath to load and save the tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.loadTaskList());
        } catch (DukeException e) {
            ui.showDukeException(e);
            taskList = new TaskList();
        }
    }

    /**
     * The method to execute Duke.
     */
    public void run() {
        ui.printLogo();
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showDukeException(e);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("main");
        new Duke("data/taskList.txt").run();
    }


    /**
     * Creates a label with the specified text and adds it to the dialog container.
     *
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);
        return textToAdd;
    }

    /**
     * A private method to handle user input and add dialog box.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(userImage)),
                DialogBox.getDukeDialog(dukeText, new ImageView(dukeImage))
        );
        userInput.clear();
    }

    /**
     * A private method to get Duke response.
     *
     * @param input The given user input.
     * @return A String, Duke's response.
     */
    private String getResponse(String input) {
        return input;
    }

    /**
     *
     * @param stage
     */
    @Override
    public void start(Stage stage) {
        // Setting up required components.
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

        // Styling.
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

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

        // Handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });
        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }
}
