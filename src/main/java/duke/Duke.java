package duke;

import duke.task.TaskList;
import duke.util.DukeException;
import duke.util.Storage;
import duke.util.Ui;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import java.nio.file.Path;

/**
 * The Duke program. The input loop is abstracted here.
 */
public class Duke {
    /** Gui Variables */
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /** Profile pictures */
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /** Storage file path */
    public static final String OUTER_DIR = "data";
    public static final String FILE = "taskList.txt";

    /** Instance variables */
    private final Storage myStorage;
    private TaskList taskList;
    private final Ui ui;

    /**
     * Constructor for Duke.
     *
     * @param filePath The path used to store the tasks.
     */
    public Duke(Path filePath) {
        ui = new Ui();
        myStorage = new Storage(filePath);
        try {
            taskList = new TaskList(myStorage.load());
        } catch (DukeException err) {
            Ui.displayMessage(err.getMessage());
            taskList = new TaskList();
        }
    }
//
//    /**
//     * The input loop. Handles user input, creates tasks and outputs messages accordingly.
//     */
//    private void inputLoop() {
//        boolean canContinue = true;
//        while (canContinue) {
//            String input = ui.readCommand();
//            try {
//                Command command = Parser.parse(input);
//                command.execute(taskList, ui, myStorage);
//                myStorage.updateTaskListToFile(taskList);
//                canContinue = !command.isExit();
//            } catch (DukeException err) {
//                Ui.displayMessage(err.getMessage());
//            }
//        }
//    }
//
//
//    /**
//     * Runs Duke.
//     */
//    public void run() {
//        if (!myStorage.didTaskFileExist()) {
//            Ui.displayMessage(WELCOME_MESSAGE);
//        } else {
//            Ui.displayMessage(REWELCOME_MESSAGE);
//        }
//        inputLoop();
//        ui.closeScanner();
//        Ui.displayMessage(EXIT_MESSAGE);
//    }

//    public static void main(String[] args) {
//        new Duke(Paths.get(OUTER_DIR, FILE)).run();
//    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     *
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }

}
