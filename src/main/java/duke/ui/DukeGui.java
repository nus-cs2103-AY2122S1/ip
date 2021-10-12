package duke.ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;

import duke.DukeStorage;
import duke.classes.DukeParser;
import duke.classes.TaskList;
import duke.classes.exceptions.DukeException;

/**
 * Main GUI class handling the user display
 */
public class DukeGui {
    //Duke Assets
    private final DukeStorage storage;
    private TaskList taskList;
    private final DukeParser parser;

    /**
     * Class Constructor
     */
    public DukeGui() {
        //Duke Initialization
        storage = new DukeStorage();

        try {
            taskList = new TaskList(storage.retrieve("localList.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred with the saved file");
            e.printStackTrace();
            System.exit(0);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }

        parser = new DukeParser(taskList);
    }

    /**
     * Tests the Response of the user to generate an appropriate response
     *
     * @param input Input String from the user
     */
    protected String newResponse(String input) {
        String output;
        try {
            output = parser.parse(input);
            storage.saveList(taskList);
        } catch (DukeException e) {
            output = e.getMessage();
        } catch (DateTimeParseException ex) {
            output = "Date was entered incorrectly, use yyyy-MM-dd";
        } catch (IOException e) {
            output = "A file error occurred.";
        }
        return output;
    }
}
