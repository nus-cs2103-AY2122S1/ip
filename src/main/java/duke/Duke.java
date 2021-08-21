package duke;

import duke.command.Command;

class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private static final String FILE_URL = "data/storedList.txt";

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadStorage());
    }

    public static void main(String[] args) {
        new Duke(FILE_URL).run();
    }

    /**
     * Runs the program with the correct components and storage file set-ups.
     */
    public void run() {
        ui.displayGreetings();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
}