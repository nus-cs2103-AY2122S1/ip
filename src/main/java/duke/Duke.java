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
        return "Duke heard: " + input;
    }


    /**
     * Initial startup code and loading from saved data if found is done here. Saved data filename and location is
     * hardcoded in this implementation. data folder will be created if none exists.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("frosty.txt", ui);
        try {
            tasklist = new TaskList(storage.load(ui));
            ui.printLine();
        } catch (IOException | DukeException e) {
            ui.notifyLoadingError();
//            e.printStackTrace();
            tasklist = new TaskList();
        }
    }

    /**
     * Highest level of program logic and exception handling is in this method. To be called only by main().
     */
    public void run() {
        ui.init();
        boolean isExit = false;
        while (!isExit) {
            try {
                String in = ui.readCommand();
                Command c = Parser.parse(in);
                c.execute(tasklist, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.notifyEmptyDescription();
//                e.printStackTrace();
            } catch (IndexOutOfBoundsException e) {
                ui.notifyIndexOutOfBounds();
//                e.printStackTrace();
            } catch (NumberFormatException e) {
                ui.notifyImproperIndex();
//                e.printStackTrace();
            } catch (DateTimeParseException e) {
                ui.notifyImproperDateTime();
//                e.printStackTrace();
            } finally {
                ui.printLine();
            }
        }
        try {
            storage.save(tasklist, ui);
        } catch (IOException e) {
            ui.notifySavingError();
//            e.printStackTrace();
        }
        ui.closing();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
