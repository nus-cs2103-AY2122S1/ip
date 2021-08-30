package kermit;

import kermit.command.Command;

/**
 * Driver class for Kermit, a frog that keeps tracks of your tasks.
 */
public class Kermit {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructor for Kermit.
     *
     * @param filePath File path for where data for task list should be stored.
     */
    public Kermit(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (KermitException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * Driver function to start Kermit.
     */
    public void run() {
        ui.showIntroMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                command.execute(taskList, ui, storage);
                isExit = command.isExit();
            } catch (KermitException e) {
                ui.showErrorMessage(e.getMessage());
            }
        }
    }

    /**
     * Starts Kermit and saves data in data/tasks.txt.
     *
     * @param args  The commandline arguments.
     */
    public static void main(String[] args) {
        new Kermit("data/tasks.txt").run();
    }
}
