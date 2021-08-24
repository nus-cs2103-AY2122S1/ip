package duke;
import duke.exception.DukeException;

import java.io.*;

/**
 * Duke contains a main method which runs the program.
 *
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private UI ui;

    /**
     * Constructor of Duke. UI object, storage and tasks are instantiated.
     *
     * @param filePath Path to the file that saves all the task.
     */
    public Duke(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.printErrorMessage(e.getMessage());
            tasks = new TaskList();
        } catch (IOException e) {
            ui.printErrorMessage("File failed to load.");
        }
    }

    /**
     * A method that allows user give input and runs the program.
     * Contains a parser which reads command from the input.
     */
    public void run() {
        try {
            Parser parser = new Parser(tasks, ui, storage);
            parser.readTask();
        } catch (IOException e) {
            ui.printErrorMessage("File fails to update");
        }
    }


    public static void main(String[] args) {
        Duke duke = new Duke(".\\src\\main\\level-7.txt");
        duke.run();
    }
}
