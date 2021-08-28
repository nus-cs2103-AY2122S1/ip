package duke;

import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;

import duke.command.Command;
import duke.task.TaskList;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private UI ui;

    /**
     * The main Duke class that will be run.
     *
     * @param filePath The filepath of the saved file
     */
    public Duke(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
            run(filePath);
        } catch (FileNotFoundException e) {
            ui.printLoadingError(filePath);
        }
    }

    /**
     * Runs the Duke program.
     *
     * @param filePath Filepath of the text file where tasks data is stored.
     */
    public void run(String filePath) {
        ui.printWelcome();
        boolean isExit = false;

        // Commands
        while (!isExit) {
            try {
                String command = ui.readCommand();
                Command c = Parser.parse(command);
                c.execute(this.tasks, this.ui, this.storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.printDukeException(e);
            } catch (IndexOutOfBoundsException e) {
                ui.printIndexOutOfBoundsException();
            } catch (NumberFormatException e) {
                ui.printNumberFormatException();
            } catch (FileNotFoundException e) {
                ui.printLoadingError(filePath);
            } catch (DateTimeParseException e) {
                ui.printDateTimeParseException();
            }
        }

        ui.printGoodBye();
    }

    public static void main(String[] args) {
        new Duke("./data/task_list.txt");
    }
}
