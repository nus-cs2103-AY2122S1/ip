package duke.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * Text UI of the application.
 */
public class Ui {
    private static final String SEPARATOR =
            "-------------------------------------------------------";
    private final Image user = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private final Image duke = new Image(this.getClass().getResourceAsStream("/images/duke.png"));

    private final Scanner in;
    private final PrintStream out;
    private final VBox dialogContainer;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
        this.dialogContainer = null;
    }

    public Ui(VBox dialogContainer) {
        this(System.in, System.out, dialogContainer);
    }

    public Ui(InputStream in, PrintStream out, VBox dialogContainer) {
        this.in = new Scanner(in);
        this.out = out;
        this.dialogContainer = dialogContainer;
    }

    /**
     * Prompts for the command and reads the text entered by the user.
     *
     * @return The full command entered by the user
     */
    public String getUserInput() {
        out.print(">>> ");
        String input = in.nextLine();

        input = input.trim();
        input = input.replaceAll("~", "");

        return input;
    }

    private Label makeLabel(String message) {
        Label temp = new Label(message);
        temp.setStyle("-fx-text-fill: white; -fx-font-weight: 700; -fx-background-color: #325d79;"
                + "-fx-padding: 20px; -fx-background-radius: 20px");
        temp.setWrapText(true);
        return temp;
    }

    private void showDukeUiMessage(String message) {
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(makeLabel(message), new ImageView(duke))
        );
    }

    public void showUserUiMessage(String message) {
        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(makeLabel(message), new ImageView(user))
        );
    }

    public void showWelcomeMessage() {
        printMsg("Hello! I'm Duke\n    I am your personal to-do list!");
    }

    public void showGoodbyeMessage() {
        printMsg("Bye! Hope to see you again soon!");
    }

    public void showErrorMessage(Exception e) {
        printMsg(e.getMessage());
    }

    /**
     * Shows message(s) to the user.
     *
     * @param msgs Array of message(s)
     */
    public void printMsg(String... msgs) {
        out.println(SEPARATOR);
        for (String msg:msgs) {
            out.println(msg);
        }
        out.println(SEPARATOR);

        if (dialogContainer != null) {
            StringBuilder toDisplay = new StringBuilder();
            for (String msg:msgs) {
                toDisplay.append(String.format("%s\n", msg));
            }
            showDukeUiMessage(toDisplay.toString());
        }
    }
}
