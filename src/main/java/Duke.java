import java.nio.file.Paths;
import java.time.format.DateTimeParseException;

/**
 * This Duke class implements the functionalities of a chatbot,
 * helping a person to keep track of various things.
 *
 * @author Yeo Jun Wei
 */
public class Duke {

    /** Path of the file that stores the tasks in the hard disk */
    private static final String FILE_PATH = String.valueOf(Paths.get(
            System.getProperty("user.home"), "data", "dukeFile.txt"));

    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
        parser = new Parser();
    }

    public void run() {
        ui.greetUser();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (DateTimeParseException e) {
                ui.showError("Please specify the date in this format: yyyy-mm-dd");
            }
        }
    }

    public static void main(String[] args) {
        new Duke(FILE_PATH).run();
    }
}