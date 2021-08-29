package duke;

import duke.commands.Command;

/**
 * The main class which runs the Duke chat bot.
 */
public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    public Duke(String filePath) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
        try {
            taskList = storage.load();
        } catch (Exception e) {
            System.out.println(e);
            ui.showLoadingError();
            storage.createNewFile();
            taskList = new TaskList();
        }
    }

    /**
     * Runs
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}