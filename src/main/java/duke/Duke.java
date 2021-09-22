package duke;

import duke.ui.MainWindow;
import duke.ui.UserInterface;
import duke.command.Command;

import java.time.format.DateTimeFormatter;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Represents a runner for the Duke program.
 */
public class Duke {

    private UserInterface ui;
    private DateTimeFormatter dtformatter;
    private Storage storage;
    private TaskList taskList;

    /**
     * Class constructor.
     */
    public Duke(MainWindow mw) {
        ui = new UserInterface(mw);
        dtformatter = DateTimeFormatter.ISO_DATE;
        String home = System.getProperty("user.home");
        Path dukePath = Paths.get(home, "Documents", "duke", "data.csv");
        storage = new Storage(dukePath);
        taskList = storage.load(ui);
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public void respond(String input) {
        try {
            Command command = Parser.parse(input);
            command.execute(taskList, ui);
        } catch (Exception ex) {
            ui.displayError(ex.getMessage());
        }
    }

    public void save() {
        storage.save(ui, taskList);
    }
}
