package duke;

import java.io.IOException;

import duke.commands.Command;
import javafx.application.Platform;
import javafx.scene.image.Image;

/**
 * Container for Duke program.
 */
public class Duke {
    private static final String FILE_PATH = "data/tasks.txt";

    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.JPG"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.JPG"));

    /**
     * Instantiates a Duke instance with default file path "data/tasks".
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);

        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.printError(e.getMessage());
        }
    }

    /**
     * Returns chat-bot response depending on the user input.
     *
     * @param input User input.
     * @return Chat-bot response.
     */
    String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            boolean isExit = command.isExit();

            if (isExit) {
                Platform.exit();
            }

            return command.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return ui.printError(e.toString());
        }
    }

}
