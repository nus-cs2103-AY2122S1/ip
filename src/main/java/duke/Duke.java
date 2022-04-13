package duke;

import duke.commands.Command;
import duke.gui.Main;
import javafx.application.Application;

/**
 * The main class which runs the Duke chatbot.
 */
public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    /**
     * Constructs a Duke object.
     */
    public Duke() {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage("data/tasks.txt");
        try {
            taskList = storage.load();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ui.showLoadingError();
            storage.createNewFile();
            taskList = new TaskList();
        }
    }

    /**
     * Gets the response from Duke based on the input from the user.
     *
     * @param input The input String entered by the user.
     * @return The response from Duke.
     */
    public String getResponse(String input) {
        try {
            Command c = parser.parse(input);
            String response = c.execute(taskList, ui, storage);
            if (c.isExit()) {
                System.exit(0);
            }
            assert !response.isEmpty() : "Response should not be empty";
            return response;
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }

    /**
     * Returns the TaskList.
     *
     * @return The TaskList
     */
    public TaskList getTaskList() {
        return taskList;
    }

    /**
     * Returns the Ui.
     * @return The Ui.
     */
    public Ui getUi() {
        return ui;
    }

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
