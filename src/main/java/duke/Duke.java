package duke;

import java.io.IOException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException ioException) {
            System.out.println(ioException);
            tasks = new TaskList();
        }
        this.parser = new Parser(this.tasks);
    }

    public static void main(String[] args) throws DukeException, IOException {
        new Duke("src/data/duke.txt").chat();
    }

    /**
     * Prints (to screen) duke.Duke's response to the user input, entered from the Command Line.
     */
    private void chat() throws DukeException {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                duke.command.Command c = parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }
}
