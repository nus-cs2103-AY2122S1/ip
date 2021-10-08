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
     * Starts application and loads data if it exists. Saved data filename and location is
     * hardcoded in this implementation and data folder will be created if none is found.
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

    /**
     * Returns response from the chat application given users input.
     *
     * @param input input from the user
     * @return String to be displayed to the user
     */
    public String getResponse(String input) {
        String response = "";
        try {
            Command c = Parser.parse(input);
            response = c.execute(tasklist, ui, storage);
        } catch (DukeException e) {
            return ui.notifyError(e);
        } catch (IndexOutOfBoundsException e) {
            return ui.notifyIndexOutOfBounds();
        } catch (NumberFormatException e) {
            return ui.notifyImproperIndex();
        } catch (DateTimeParseException e) {
            return ui.notifyImproperDateTime();
        }
        return response;
    }
}
