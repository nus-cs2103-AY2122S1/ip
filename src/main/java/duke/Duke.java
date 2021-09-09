package duke;

import java.io.IOException;

import duke.command.Command;
import duke.command.WelcomeCommand;
import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Duke is a educational software project designed to take you through
 * the steps of building a small software incrementally, while applying
 * as many Java and SE techniques as possible along the way.
 */
public class Duke {
    private String response;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
    }

    /**
     * Constructs a Duke with the specified filePath.
     *
     * @param filePath Path of the file to store the task list.
     * @param isGui    Set to true if Duke is on GUI.
     */
    public Duke(String filePath, boolean isGui) {
        if (isGui) {
            this.setUpGui(filePath);
        } else {
            this.setUpUi(filePath);
        }

    }

    private void setUpGui(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load(false));
            String successMessage = "Data loaded successfully!";
            this.setResponse(successMessage);
        } catch (DukeException | IOException e) {
            String errorMessage = "☹ OOPS!!! Seems like your data is corrupted. "
                + "Please make sure you data file has the correct format.";
            this.setResponse(errorMessage);
            tasks = new TaskList();
        }
    }

    private void setUpUi(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load(false));
        } catch (DukeException | IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Sets Duke's response.
     *
     * @param response Duke's response.
     */
    public void setResponse(String response) {
        this.response = response;
    }

    /**
     * Returns Duke's most recent response.
     */
    public String getResponse() {
        return this.response;
    }


    /**
     * Returns Duke's response based on the input.
     *
     * @param input User input.
     * @return Duke's response.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(this, tasks, storage);
            return this.getResponse();
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Returns a warm welcome by Duke.
     *
     * @return Duke's welcome statement.
     */
    public String getWelcomeResponse() {
        Command c = new WelcomeCommand();
        try {
            c.execute(this, tasks, storage);
            return this.getResponse();
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Runs text-based UI.
     */
    public void run() {
        this.sayWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(this, tasks, storage);
                ui.showMessage(this.getResponse());
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    private void sayWelcome() {
        ui.showLine();
        Command c = new WelcomeCommand();
        try {
            c.execute(this, tasks, storage);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
        ui.showMessage(this.getResponse());
        ui.showLine();
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt", false).run();
    }

}
