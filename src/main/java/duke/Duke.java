package duke;

import duke.command.Command;
import java.time.format.DateTimeParseException;
import java.io.IOException;

/**
 * Class containing main() method. Exception handling and highest level of program logic is implemented here.
 */
public class Duke {
    private static TaskList tasklist;
    private static Ui ui;
    private static Storage storage;

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
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
}
