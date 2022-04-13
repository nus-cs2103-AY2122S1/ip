package chadbot;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;

import chadbot.data.Storage;
import chadbot.data.TaskList;
import chadbot.io.Parser;
import chadbot.io.Ui;


public class Chad {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Returns a new Chad object.
     *
     * @param filePath Path to the file storing saved data and to save new data to.
     */
    public Chad(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (ChadException e) {
            ui.showChadException(e);
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
            ui.showLoadingError();
        }
    }

    /**
     * Returns a String that corresponds to a user input command.
     *
     * @param input Path to the file storing saved data and to save new data to.
     * @return String to be used as Chad output that corresponds to a user input command.
     */
    public String getResponse(String input) {
        assert storage != null : "Storage should not be null";
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (ChadException chadException) {
            return ui.showChadException(chadException);
        } catch (IOException ioException) {
            return ui.showSavingError();
        } catch (DateTimeParseException dateTimeParseException) {
            return ui.showDateTimeException();
        } catch (NumberFormatException numberFormatException) {
            return ui.showIntError();
        }
    }

}
