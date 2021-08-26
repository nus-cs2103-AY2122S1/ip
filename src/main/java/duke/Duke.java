package duke;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Duke {
    private static final Path DATA_PATH = Paths.get(
        System.getProperty("user.dir"),
        "data",
        "duke.txt"
    );

    /**
     * Main method for Duke class.
     * @param args List of arguments.
     */
    public static void main(String[] args) {
        TaskList tasks = new TaskList();
        Storage storage = new Storage(DATA_PATH);

        Ui.printWelcome();
        try {
            tasks = storage.readTaskList();
        } catch (Exception e) {
            Ui.printException(e);
            return;
        }

        var sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            var command = sc.nextLine();
            try {
                boolean isEnd = Parser.runCommand(tasks, storage, command);
                if (isEnd) {
                    break;
                }
            } catch (Exception e) {
                Ui.printException(e);
            }
        }
        sc.close();
    }
}
