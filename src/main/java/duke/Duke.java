package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
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

}
