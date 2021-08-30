package duke;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import duke.command.Command;

/**
 * Class containing main() method. Exception handling and highest level of program logic is implemented here.
 */
public class Duke {
    private static TaskList tasklist;
    private static Ui ui;
    private static Storage storage;

    /**
     * Initial startup code and loading from saved data if found is done here. Saved data filename and location is
     * hardcoded in this implementation. data folder will be created if none exists.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("frosty.txt", ui);
        try {
            tasklist = new TaskList(storage.load());
        } catch (IOException | DukeException e) {
            tasklist = new TaskList();
        }
    }

    String getResponse(String input) {
        String response = "";
        try {
            Command c = Parser.parse(input);
            response = c.execute(tasklist, ui, storage);
        } catch (DukeException e) {
            ui.notifyEmptyDescription();
        } catch (IndexOutOfBoundsException e) {
            ui.notifyIndexOutOfBounds();
        } catch (NumberFormatException e) {
            ui.notifyImproperIndex();
        } catch (DateTimeParseException e) {
            ui.notifyImproperDateTime();
        }
        return response;
    }
}
