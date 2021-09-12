package duke;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.format.DateTimeParseException;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * This Duke class implements the functionalities of a chatbot,
 * helping a person to keep track of various things.
 */
public class Duke {

    private static final String FILE_PATH = String.valueOf(Paths.get("data", "dukeFile.txt"));

    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    /**
     * Constructor for a Duke instance.
     */
    public Duke() {
        storage = new Storage(FILE_PATH);
        assert storage.load() != null : "An ArrayList should be loadable from the storage system";
        tasks = new TaskList(storage.load());
        parser = new Parser();
    }

    /**
     * Listens to the user input and returns the appropriate message.
     *
     * @param input The user input.
     * @return A message indicating command execution or user input error.
     */
    public String getResponse(String input) {
        try {
            Command command = parser.parse(input);
            assert command != null : "A valid Command instance should be provided";
            return command.execute(tasks, storage);
        } catch (DukeException e) {
            return e.getMessage();
        } catch (DateTimeParseException e) {
            return "Please specify the date in this format: yyyy-mm-dd";
        } catch (IOException e) {
            return "Unable to write data to file";
        }
    }
}
