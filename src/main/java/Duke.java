import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Duke class
 *
 * @author Timothy Wong Eu-Jin
 * @version A-MoreOOP
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    //Constructor
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
