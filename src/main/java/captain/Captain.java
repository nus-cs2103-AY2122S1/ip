package captain;

import captain.command.Command;
import captain.parser.TaskParser;
import captain.task.TaskList;
import javafx.scene.image.Image;

/**
 * Represents a chatbot to manage a list of tasks.
 *
 * @author Adam Ho
 */
public class Captain {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private static final String FILEPATH = "./data/taskdata.txt";
    private Image user = new Image(this.getClass().getResourceAsStream("/images/cat.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/samoyed.png"));

    /**
     * Creates a Duke chatbot object with the specified
     * text file to store existing task data.
     *
     * @param filepath The path of the file's location.
     */
    public Captain(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Returns a response to user input.
     *
     * @param input The input from the user.
     */
    public String getResponse(String input) {
        try {
            Command c = TaskParser.parseCommand(input);
            String response = c.execute(tasks, ui, storage);

            // Ensure that there is a response to return
            assert(response != null);
            assert (!response.equals(""));

            return response;
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }
}
