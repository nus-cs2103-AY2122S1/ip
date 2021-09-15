package duke;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import duke.command.Command;

import duke.task.TaskList;

/**
 * Represents the chatbot that users can save tasks with
 */
public class Duke {

    public static final String FILE_PATH = "data/tasks.txt";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Duke constructor using file path to load/save tasks.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        try {
            tasks = storage.load();
        } catch (DukeException e) {
            ui.showLoadingError(e.getMessage());
            tasks = TaskList.emptyTaskList();
        }
    }

    /**
     * Runs Duke chatbot until termination.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showLoadingError(e.getMessage());
            }
        }
    }

    /**
     * Launches Duke chatbot with specified file path.
     *
     * @param args
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Returns a response to user input.
     *
     * @param input User input to respond to.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String response = c.execute(tasks, ui, storage);

            // Ensure that there is a response to return
            assert(response != null);
            assert (!response.equals(""));

            return response;
        } catch (DukeException e) {
            return ui.showLoadingError(e.getMessage());
        }
    }

    /** Saves the tasks in given TaskList to file path of duke. */
    public void save() {
        try {
            storage.save(tasks);
        } catch (DukeException e) {
            Ui.showLoadingError(e.getMessage());
        }
    }
}
