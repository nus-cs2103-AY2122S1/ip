package lania;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

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
import lania.command.Command;
import lania.exception.LaniaException;
import lania.task.TaskList;

/**
 * Represents the chatbot Lania that helps manage your tasks.
 */
public class Lania extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/Lania.png"));


    /** Contains the task list */
    private TaskList tasks;
    /**  Deals with interactions with the user */
    private Ui ui;
    /** Deals with loading tasks from the file and saving tasks in the file */
    private Storage storage;
    /** Deals with making sense of the user command */
    private Parser parser;

    public Lania() {
        ui = new Ui();
        storage = new Storage("data/lania.txt");
        parser = new Parser();
        try {
            tasks = storage.load();
        } catch (IOException e) {
            ui.showError();
            e.printStackTrace();
        }
    }

    /**
     * Constructor for the Lania object.
     *
     * @param filePath The location of the file in which data is stored
     */
    public Lania(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
    }

    /**
     * Main part of the program. First, it tries to load the file
     * if it exists and display its contents. It then copies the
     * contents over into a TaskList.
     *
     * After greeting the user, the
     * program continuously waits for input unless the command 'bye'
     * is given which closes exits the program.
     *
     */
    public void run() {
        try {
            tasks = storage.load();
        } catch (IOException e) {
            ui.showError();
            e.printStackTrace();
        }
        ui.showListMessage(tasks);
        ui.showGreetingMessage();
        boolean isExit = false;
        Scanner s = new Scanner(System.in);
        while (!isExit) {
            try {
                String input = s.nextLine();
                Command c = parser.parse(input);
                c.execute(tasks, storage, ui);
                isExit = c.isExit();
            } catch (LaniaException e) {
                ui.showLaniaException(e);
            } catch (DateTimeParseException e) {
                ui.showDateTimeException();
            }
        }
        s.close();
    }

    /**
     * Lania object is created and starts running.
     *
     * @param args The command line arguments. Not required here.
     **/
    public static void main(String[] args) {
        Lania lania = new Lania("data/lania.txt");
        lania.run();
    }

    @Override
    public void start(Stage stage) {
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
        stage.setTitle("Lania");
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

        dialogContainer.getChildren().addAll(
                DialogBox.getLaniaDialog(new Label(ui.showGreetingMessage()), new ImageView(duke)));

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        try {
            Command c = parser.parse(userInput.getText());
            Label dukeText = new Label(c.execute(tasks, storage, ui));
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(userText, new ImageView(user)),
                    DialogBox.getLaniaDialog(dukeText, new ImageView(duke))
            );
        } catch (LaniaException e) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(userText, new ImageView(user)),
                    DialogBox.getLaniaDialog(new Label(ui.showLaniaException(e)), new ImageView(duke))
            );
        } catch (DateTimeParseException e) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(userText, new ImageView(user)),
                    DialogBox.getLaniaDialog(new Label(ui.showDateTimeException()), new ImageView(duke))
            );
        }
        userInput.clear();
    }
}
