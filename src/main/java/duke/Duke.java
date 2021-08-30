package duke;

import parser.Parser;
import storage.Storage;
import tasks.TaskList;
import ui.Launcher;
import ui.Ui;

/** This class implements the Duke memo assistant.
 * @author damithc
 * edited by Wanyu
 */
public class Duke{
    private static TaskList tasks;
    private final Parser parser;
    private final Ui ui;
    private final Storage storage;


    /**
     * Constructs a Duke object.
     */
    public Duke() {
        Ui.welcomeMessage();
        this.ui = new Ui();
        this.storage = new Storage();
        try {
            storage.createFiles();
        } catch (DukeException e) {
            Ui.showInput("something went wrong: " + e.getMessage() + "\n"
                    + "     exiting program...");
            terminate();
        }
        tasks = new TaskList(storage.loadSaves());
        parser = new Parser();
    }

    /**
     * Starts the program and execute commands
     * detected by parser as per user input.
     */
    public void run() {
        Launcher.run();
    }

    /**
     * Exits the program.
     */
    public static void terminate() {
        Ui.showExitMessage();
        System.exit(0);
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
