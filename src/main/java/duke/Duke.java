package duke;

import java.io.IOException;
import java.time.format.DateTimeParseException;
/**
 * The Duke class is the main class.
 *
 * @author Timothy Wong Eu-Jin
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructs a Duke object to start the program.
     *
     * @param filePath relative file path of output file
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
     * Handles most errors within a while loop.
     */
    public void run() {
        this.ui.greet();
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
            } catch (IOException e) {
                System.err.println(e.getMessage());
            } catch (DateTimeParseException e) {
                System.err.println(e.getMessage());
            }
            finally {
                ui.showDivider();
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke("data/tasks.txt");
        duke.run();
    }
}
