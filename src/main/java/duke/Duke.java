package duke;

import java.io.*;
import duke.util.UI;
import duke.util.Parser;
import duke.util.Storage;
import duke.task.TaskList;
import duke.command.Command;
import duke.exception.DukeException;

/**
 * Duke, a Personal Assistant ChatBot.
 *
 * @author Frederick Pek
 * @version CS2103 AY21/22 Sem 1 iP
 */
public class Duke {
    private final TaskList taskList;
    private final Storage storage;
    private final UI ui;

    /**
     * Constructs an instance of Duke.
     * <p>
     * Duke's data will be stored in a text file denoted by the
     * specified directory and file names in the currect directory.
     *
     * @param dirName the specified directory name to store Duke's data.
     * @param fileName the specified file name to store Duke's data.
     */
    public Duke(String dirName, String fileName) {
        this.storage = new Storage(dirName, fileName);
        this.taskList = new TaskList(storage.loadTasks());
        this.ui = new UI();
    }

    private void run() {
        ui.printStartUpMessage();
        boolean isExit = false;

        while (!isExit) {
            try {
                String input = ui.readLine();
                Command command = Parser.parse(input);
                command.execute(taskList, ui, storage);
                isExit = command.isExit();
            } catch (DukeException ex) {
                ui.printErrorMessage(ex);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke("data", "duke.txt");
        duke.run();
    }
}
