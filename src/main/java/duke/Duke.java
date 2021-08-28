package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;

import duke.data.Storage;
import duke.data.TaskList;
import duke.io.Parser;
import duke.io.Ui;


public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Returns a new Duke object.
     *
     * @param filePath Path to the file storing saved data and to save new data to.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showDukeException(e);
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
            ui.showLoadingError();
        }
    }

    /**
     * Returns a String that corresponds to a user input command.
     *
     * @param input Path to the file storing saved data and to save new data to.
     * @return String to be used as Duke output that corresponds to a user input command.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException dukeException) {
            return ui.showDukeException(dukeException);
        } catch (IOException ioException) {
            return ui.showSavingError();
        } catch (DateTimeParseException dateTimeParseException) {
            return ui.showDateTimeException();
        } catch (NumberFormatException numberFormatException) {
            return ui.showIntError();
        }
    }

}
