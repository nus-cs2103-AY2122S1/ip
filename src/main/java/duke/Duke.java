package duke;

import java.io.IOException;
import java.time.format.DateTimeParseException;
/**
 * Runs the program Duke.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructs a Duke object to start the program.
     *
     * Calls storage to load the TaskList data from the hard disk.
     *
     * @param filePath relative file path of output file.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);

        try {
            this.tasks = storage.load();
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            this.tasks = new TaskList();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Runs the program
     *
     * Handles errors within a while loop.
     */
    public void run() {
        ui.greet();

        boolean isExit = false;
        while(!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showDivider();
                this.parser = new Parser(tasks, ui, storage);
                parser.parse(fullCommand);
                isExit = ui.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (IOException | DateTimeParseException e) {
                System.err.println(e.getMessage());
            } finally {
                ui.showDivider();
            }
        }
    }

    /**
     * Initialises an instance of Duke and runs it.
     *
     * @param args
     */
    public static void main(String[] args) {
        Duke duke = new Duke("data/tasks.txt");
        duke.run();
    }
}
