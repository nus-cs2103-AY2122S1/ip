package duke;

import duke.command.Command;

/**
 * A chatbot that helps manages your tasks.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Class constructor. Initialises a Duke instance with the Ui, TaskList, and Storage.
     *
     * @param filePath
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * Empty class constructor.
     */
    public Duke() {
        this("src/main/data/dukeSave.txt");
    }

    /**
     * To run the program.
     */
    public void run() {
        ui.showStartup();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parseInput(fullCommand);
                c.runCommand(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }

    }

    /**
     * This is the main method.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        new Duke("src/main/data/duke.txt").run();
    }

    /**
     * Returns a response after receiving input from user.
     *
     * @param input The user input.
     * @return The response string.
     */
    public String getResponse(String input) {
        String result;
        try {
            Command c = Parser.parseInput(input);
            result = c.runCommand(taskList, ui, storage);
        } catch (DukeException e) {
            result = e.getMessage();
        }
        return result;
    }
}
