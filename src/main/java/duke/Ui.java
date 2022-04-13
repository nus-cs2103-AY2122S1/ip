package duke;

import java.util.Scanner;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * User interface class for controlling user interaction.
 * Reads user inputs and displays outputs.
 *
 * @author Chang-CH
 */
public class Ui {
    private Scanner inputReader = null;
    private VBox dialogContainer = null;
    private final Image duke = new Image(this.getClass().getResourceAsStream("/images/duk.jpg"));
    private final Image user = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));

    /**
     * Sole constructor for invocation by Duke.
     */
    public Ui() {
        super();
        inputReader = new Scanner(System.in);
    }

    /**
     * Constructor for printing to Duke
     *
     * @param dialogContainer Container to add components to.
     */
    protected Ui(VBox dialogContainer) {
        super();
        assert dialogContainer != null;
        inputReader = new Scanner(System.in);
        this.dialogContainer = dialogContainer;
    }

    /**
     * Reads user input from scanner.
     *
     * @return user input string.
     */
    protected String readCommand() {
        return inputReader.nextLine();
    }

    /**
     * Displays a text in the GUI, if applicable.
     *
     * @param text Message to be displayed.
     */
    public void printDukeDialog(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);
        DialogBox dialogBox = DialogBox.getDukeDialog(textToAdd, new ImageView(duke));

        dialogContainer.getChildren().add(dialogBox);
    }

    /**
     * Prints out the user's input in the GUI
     *
     * @param input User input.
     */
    public void printUserDialog(String input) {
        Label textToAdd = new Label(input);
        textToAdd.setWrapText(true);
        DialogBox dialogBox = DialogBox.getUserDialog(textToAdd, new ImageView(user));

        dialogContainer.getChildren().add(dialogBox);
    }

    /**
     * Closes the scanner.
     */
    public void exit() {
        inputReader.close();
        Stage stage = (Stage) dialogContainer.getScene().getWindow();
        stage.close();
    }

    /**
     * Prints a line to separate outputs more clearly.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a message.
     *
     * @param message Message to be displayed.
     */
    public void showMessage(String message) {
        System.out.println(message);
        if (dialogContainer != null) {
            printDukeDialog(message);
        }
    }

    /**
     * Displays an error.
     *
     * @param message Error message to be displayed.
     */
    public void showError(String message) {
        System.out.println(message);
        if (dialogContainer != null) {
            printDukeDialog(message);
        }
    }

    /**
     * Displays the welcome splash screen.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _\n"
                + "| | | | | | | |/ /\n"
                + "| |_| | |_| |   <  \n"
                + "|____/ \\__,_|_|\\_\\\n";
        System.out.println(logo);

        String greeting = "Hello! I'm Duk\n"
                + "What must I do for you?\n";
        System.out.println(greeting);

        if (dialogContainer != null) {
            printDukeDialog(greeting);
        }
    }
}
