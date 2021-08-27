package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;

import duke.data.Storage;
import duke.data.TaskList;
import duke.io.Parser;
import duke.io.Ui;

class Duke {

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
     * Runs the bot Duke.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                Command c = Parser.parse(ui.readCommand());
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (NumberFormatException numExcep) {
                ui.showIntError();
            } catch (DukeException dukeExcep) {
                ui.showDukeException(dukeExcep);
            } catch (DateTimeParseException dtExcep) {
                ui.showDateTimeException();
            } catch (IOException ioExcep) {
                ui.showSavingError();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("duke.txt").run();
    }
}
