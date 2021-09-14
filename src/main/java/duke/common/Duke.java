package duke.common;

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

    public static class DukeException extends Exception {
        public DukeException(String errorMessage) {
            super(errorMessage);
        }
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

    /**
     * Passes user input to Duke runtime and relays response back.
     *
     * @param input user input
     * @return Duke response
     */
    public String getResponse(String input) {
        String response = parser.parse(input, taskList, ui);
        storage.store(taskList);
        return response;
    }
}
