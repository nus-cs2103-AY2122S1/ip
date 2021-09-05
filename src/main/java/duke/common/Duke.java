package duke.common;

import duke.common.Parser;
import duke.common.Storage;
import duke.common.TaskList;
import duke.common.Ui;
import javafx.scene.image.Image;

public class Duke {
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private Ui ui;
    private Parser parser;
    private Storage storage;
    private TaskList taskList;

    private String taskListFileName;

    public Duke() {
        this("data/duke.txt");
    }

    /**
     * Configures a Duke runtime to use a taskList storage file at specified location.
     * This exposes the storage file location dependency for testing purposes.
     *
     * @param taskListFileName path to taskList storage file.
     */
    public Duke(String taskListFileName) {
        this.taskListFileName = taskListFileName;
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(this.taskListFileName);
        taskList = storage.initialise();
    }

    public static class DukeException extends Exception {
        public DukeException(String errorMessage) {
            super(errorMessage);
        }
    }
    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        String response = parser.parse(input, taskList, ui);
        storage.store(taskList);
        return response;
    }
}
