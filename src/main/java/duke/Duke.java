package duke;

import java.io.IOException;

public class Duke {
    private final Ui ui;
    private final Storage storage;
    private TaskList tasks;

    /**
     * Constructor loads the save file from the specified filepath.
     *
     * @param filepath The path to the save file (if any).
     */
    public Duke(String filepath) {
        this.ui = new Ui();
        this.storage = new Storage(filepath);

        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            Ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Starts the Duke application.
     */
    private void startApp() {
        String command;
        Ui.showWelcomeText();

        while (!(command = ui.readCommand().trim()).equals("bye")) {
            try {
                String msg = Parser.parse(command).execute(this.tasks);
                this.storage.save(this.tasks.toSaveFormat());
                Ui.printMessage(msg);
            } catch (DukeException e) {
                Ui.printMessage(e.getMessage() + "\n");
            }
        }

        Ui.showEndText();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").startApp();
    }
}
