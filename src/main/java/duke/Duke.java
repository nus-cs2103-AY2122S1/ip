package duke;

import duke.exceptions.DukeException;
import duke.storage.Storage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Main class for Duke.
 */
public class Duke {
    static String border = "--------------------------------------------------";

    /**
     * Main entry point for Duke project.
     *
     * @param args unused.
     */
    public static void main(String[] args) {
        UI ui = new UI();

        Path storagePath = Paths.get(".", "data", "duke.txt");
        TaskArrayList taskList;
        try {
            taskList = Storage.load(storagePath);
        } catch (DukeException | IOException e) {
            taskList = new TaskArrayList();
            ui.showLoadingError();
        }
        ui.displayLogo();
        ui.displayWelcome();

        Scanner userScanner = new Scanner(System.in);
        Parser parser = new Parser(ui, taskList, storagePath);

        boolean isExit = false;
        while (!isExit) {
            String userInput = userScanner.nextLine();
            try {
                isExit = parser.run(userInput);
            } catch (DukeException e) {
                ui.displayException(e);
            } finally {
                Storage.dump(taskList, storagePath);
            }
        }
    }
}
