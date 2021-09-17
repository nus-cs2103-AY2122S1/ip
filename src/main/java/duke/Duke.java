package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.gui.DialogBox;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.Image;


public class Duke {


    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private Storage storage;
    private TaskList taskList;

    /**
     * Constructor for Duke object.
     *
     * @param filePath the file path of the document which stores the users tasks.
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.taskList = this.storage.load();
    }

    /**
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
     * Gets the program output give the user input.
     *
     * @param input User input
     * @return Duke's response
     */
    public String getResponse(String input) {
        if (input.equals("exit")) {
            Platform.exit();
        }
        try {
            Command command = Parser.parse(input);
            return command.execute(this.taskList, input, this.storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }


    //    public static void main(String[] args) {
    //        new Duke("Data\\taskList.txt").run();
    //    }
}
