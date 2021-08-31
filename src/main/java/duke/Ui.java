package duke;

import java.util.Scanner;

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

public class Ui extends Application {
    /** A Scanner object to read user command. **/
    private final Scanner sc;

    private final Duke duke;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/lehao.jpg"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/zixuan.jpg"));

    /**
     * A public constructor to initialized the Ui.
     */
    public Ui(Duke duke) {
        this.sc = new Scanner(System.in);
        this.duke = duke;
    }

    private void showDukeResponse(String dukeResponse) {
        Label dukeText = new Label(dukeResponse);
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(dukeText, new ImageView(dukeImage))
        );
    }

    /**
     * A private method to show user input.
     */
    private void showUserInput() {
        Label userText = new Label(userInput.getText());
        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(userText, new ImageView(userImage))
        );
        try {
            duke.getResponse(userInput.getText());
        } catch (DukeException e) {
            showDukeResponse(e.getMessage());
        }
        userInput.clear();
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

        // Styling.
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

        printLogo();
        greet();
        // Handle user input.
        sendButton.setOnMouseClicked((event) -> {
            showUserInput();
        });
        userInput.setOnAction((event) -> {
            showUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * A method to print the Duke logo.
     */
    public void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        showDukeResponse("Hello from\n" + logo);
    }

    /**
     * A public method to output the greeting message.
     */
    public void greet() {
        printMessage(
            "Hello! I'm duke.Duke",
            "What can I do for you?");
    }

    /**
     * A public method to print message with certain indentation and format.
     * Receive an array of String, and output one String per line.
     *
     * @param messages The given messages to be print, one message per row.
     */
    public void printMessage(String ...messages) {
        String dukeResponseToShow = new String();
        System.out.println("    ____________________________________________________________");
        dukeResponseToShow += "    __________________________________________________\n";
        int n = messages.length;
        for (int i = 0; i < n; i++) {
            System.out.print("     ");
            System.out.println(messages[i]);
            dukeResponseToShow += "     " + messages[i] + "\n";
        }
        System.out.println("    ____________________________________________________________\n");
        dukeResponseToShow += "    __________________________________________________\n";
        showDukeResponse(dukeResponseToShow);
    }

    /**
     * A method to read user command.
     *
     * @return A String, user command.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * A method to print the message of DukeException.
     *
     * @param e The given DukeException to be shown.
     */
    public void showDukeException(DukeException e) {
        this.printMessage(e.getMessage());
    }
}
