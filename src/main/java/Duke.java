import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.FolderNotFoundException;
import duke.command.Command;
import duke.task.Task;

/**
 * <h1> Duke TaskList ChatBot! </h1>
 * The duke program implements and application that keeps track of all the users task!
 * The user is able to add, delete and "mark as complete" tasks from the task list!
 * Currently the Duke ChatBot is only able to keep track of ToDos, Deadlines and Events!
 *
 * @author Ong Cheng Seong
 * @version 1.0
 * @since 2021-08-31
 */

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a Duke ChatBot Object that has a Ui, and a TaskList populated by the data read from the storage.
     * @param filePath text document that contains the task list for Storage to read from.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Starts the Program.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Main method which starts the Duke bot.
     * @param args user input
     * @throws FileNotFoundException when filepath does not exist.
     * @throws FolderNotFoundException when filepath folder does not exist.
     * @throws IOException On input error.
     * @see IOException
     */
    public static void main(String[] args) throws FileNotFoundException, FolderNotFoundException, IOException {
        new Duke("../../../data/tasks.txt").run();
    }
}
