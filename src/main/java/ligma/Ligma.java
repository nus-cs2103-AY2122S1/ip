package ligma;

import ligma.command.Command;

import java.time.format.DateTimeParseException;
import java.util.*;
import java.io.IOException;

/**
 * This class represents a Ligma program that keeps track of your to-do list.
 * Storage object corresponds to the persistent storage
 * for the Ligma program executed and TaskList object corresponds
 * to the existing tasks during execution.
 */
public class Ligma {
    private static Storage storage;
    private TaskList tasks;

    public Ligma(String filePath) {
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (IOException e) {
            Ui.printErrorMessage("Failed to load in tasks.");
        }
    }

    /**
     * Executes Ligma program.
     */
    public void run() {
        Ui.introduceSelf();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            try {
                Command c = Parser.parseCommand(sc.nextLine());
                c.execute(tasks, storage);
                if (c.isExit()) break;
            } catch (InputMismatchException | NoSuchMethodException e1) {
                Ui.printErrorMessage(e1);
            } catch (DateTimeParseException e2) {
                Ui.printErrorMessage("Time must be in yyyy-mm-dd format.");
            }
        }
    }

    public static void main(String[] args) {
        new Ligma("data/ligma.txt").run();
    }

}
