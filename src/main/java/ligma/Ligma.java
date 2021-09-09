package ligma;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

import ligma.command.Command;
import ligma.ui.Ui;

/**
 * This class represents a Ligma program that keeps track of your to-do list.
 * Storage object corresponds to the persistent storage
 * for the Ligma program executed and TaskList object corresponds
 * to the existing tasks during execution.
 */
public class Ligma {
    private static Storage storage;
    private TaskList tasks;

    /**
     * Constructor for a Ligma object.
     *
     * @param filePath Location of file to store data
     */
    public Ligma(String filePath) throws IOException {
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.load());
    }

    /**
     * Performs tasks based on user input, essentially the
     * point of entry for Ligma's program execution
     *
     * @param input User input
     * @return Ligma's reply to be shown on GUI
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parseCommand(input);
            return c.execute(tasks, storage);
        } catch (InputMismatchException
                | NoSuchMethodException
                | IllegalArgumentException e1) {
            return e1.getMessage();
        } catch (DateTimeParseException e2) {
            return e2.getMessage();
        }
    }

}
