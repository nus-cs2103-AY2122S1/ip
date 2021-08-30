package duke;

import duke.command.Command;
import duke.exception.DukeIndexInputException;
import duke.exception.DukeTaskDetailsException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;

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


public class Duke extends Application {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

	private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    public Duke() {
        this.storage = new Storage(Paths.get(".", "data"),
                                   Paths.get(".", "data", "tasks.txt"));
        this.tasks = new TaskList(this.storage.getTasks());
        this.ui = new Ui();
    }

	/*
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
		*/

    private void run() {
        this.ui.greet();
        Scanner sc = new Scanner(System.in);
        Boolean run = true;
        while (run && sc.hasNextLine()) {
            String input = sc.nextLine();
            System.out.println(Ui.DIVIDER_LINE);
            input = input.strip();
            String[] splitInput = input.split(" ", 2);
            Command command = Parser.parse(splitInput);
            try {
                switch (command) {
                case EXIT:
                    this.ui.exit();
                    run = false;
                    break;
                case LIST:
                    this.ui.list(this.tasks);
                    break;
                case INDEXCOMMAND:
                    Task task = this.tasks.indexCommand(splitInput);
                    this.ui.indexCommand(this.tasks.size(), task, splitInput);
                    break;
                case ADDCOMMAND:
                    Task addedTask = this.tasks.addTask(splitInput);
                    this.ui.addTask(addedTask, this.tasks.size());
                    break;
                case FIND:
                    ArrayList<Task> list = this.tasks.find(splitInput[1]);
                    this.ui.list(list);
                    break;
                default:
                    this.ui.unknownCommand();
                }
            } catch (DukeTaskDetailsException | DukeIndexInputException e) {
                System.out.println("\t" + e.toString());
            }
            System.out.println(Ui.DIVIDER_LINE);
            this.storage.saveTasks(this.tasks.getTasks());
        }
        sc.close();
    }

	@Override
    public void start(Stage stage) {
        //Step 1. Formatting the window to look as expected.

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
			dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
			userInput.clear();
		});

		userInput.setOnAction((event) -> {
			dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
			userInput.clear();
		});

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

}
