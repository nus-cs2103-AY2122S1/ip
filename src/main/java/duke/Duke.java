package duke;

import java.io.IOException;
import java.util.Scanner;

import duke.commands.Command;

/**
 * Represents the chatbot Duke.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke class.
     *
     * @param pathname the path of the save file.
     * @param dir      the directory containing the save file.
     * @throws IOException when an IO operation fails.
     */
    public Duke(String pathname, String dir) throws IOException {
        this.storage = new Storage(pathname, dir);
        this.tasks = new TaskList(storage.load());
        this.ui = new Ui(new Scanner(System.in));
    }

    /**
     * Method used to run the program.
     */
    public void run() {
        Parser parser = new Parser();
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String commandLine = ui.readCommand();
                Command c = parser.parse(commandLine, tasks);
                c.execute(tasks, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                System.out.println("***WARNING*** An error has occurred Master Wayne: " + e.getMessage());
            }
        }
    }

    /**
     * The main method of Duke.
     *
     * @param args
     * @throws IOException when an IO operations fails.
     */
    public static void main(String[] args) throws IOException {
        new Duke("data/tasks.txt", "data").run();
    }
}
