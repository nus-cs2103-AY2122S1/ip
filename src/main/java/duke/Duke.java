package duke;

import java.io.IOException;

import command.Command;
import exceptions.DukeException;

/**
 * Duke is a bot capable of storing a todo list with an interactive interface.
 *
 * @author Quan Teng Foong
 */
public class Duke {

    private static TaskList taskList;
    private Storage storage;
    private Ui ui;

    /**
     * Constructor for Duke. Creates a new storage file at filepath: ./storage/save.txt
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("./storage/save.txt");
        try {
            taskList = new TaskList(storage.retrieve());

        } catch (DukeException e) {
            ui.showError(e.getMessage());
            ui.showList(taskList);
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }
    }

    //Commented out but might use in future
    ///**
    // * Runs a Duke instance.
    // */
    //public void run() {
    //    ui.greet();
    //    boolean isExit = false;
    //    while (!isExit) {
    //        try {
    //            String fullCommand = ui.readCommand();
    //            Command c = Parser.parse(fullCommand);
    //            c.execute(taskList, ui, storage);
    //            isExit = c.isExit();
    //        } catch (DukeException e) {
    //            ui.showError(e.getMessage());
    //        }
    //    }
    //    try {
    //        storage.save(taskList);
    //    } catch (IOException e) {
    //        ui.showError("ERROR: TaskList could not be saved!");
    //    }
    //}

    public static void main(String[] args) {
        new Duke();
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(taskList, ui, storage);
        } catch (DukeException | IOException e) {
            e.printStackTrace();
            return "bruh";
        }
    }
}
