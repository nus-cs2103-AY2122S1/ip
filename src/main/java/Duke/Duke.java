package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
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


public class Duke extends Application implements Runnable {
    private static final String FILE_PATH = "duke.txt";
    private static final String ERR_CREATE_FILE = "Could not create empty file.";
    private static final String ERR_UNEXPECTED = "Unexpected error occurred.";
    private Parser parser;
    private TaskList tasks;
    private Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Returns a Duke object. IOException is thrown if file does not
     * exist and is unable to be created.
     *
     * @throws IOException if file does not exist and cannot be created
     */
    public Duke() throws IOException {
        this.ui = new Ui();
        this.parser = new Parser();
        this.tasks = new TaskList(new Storage(FILE_PATH));
    }

    /**
     * Runs Duke object and starts taking in commands from user.
     */
    public void run() {
        ui.showWelcome();
        Scanner sc = new Scanner(System.in);
        String input;
        while (!(input = sc.nextLine().trim()).equals("bye")) {
            ui.showMessage(parser.execute(input, tasks));
        }
        sc.close();
        ui.showFarewell();
    }


    /**
     * Setup UI elements and start Duke GUI.
     * Taken from https://se-education.org/guides/tutorials/javaFxPart2.html
     *
     * @param stage javafx stage
     */
    @Override
    public void start(Stage stage) {
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

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }

    /**
     * Class entrypoint.
     */
    public static void main(String[] args) {
        try {
            new Duke().run();
        } catch (FileNotFoundException e) {
            System.out.println(ERR_CREATE_FILE);
        } catch (IOException e) {
            System.out.println(ERR_UNEXPECTED);
        }
    }
}
