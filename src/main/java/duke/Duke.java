package duke;

import java.io.IOException;
import java.util.Scanner;

import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class Duke {

    /** Storage object to interact with the data file */
    private Storage storage;
    /** List of all Tasks */
    private TaskList tasks;
    /** Ui object to print output to user */
    private Ui ui;

    public enum Type {
        TODO,
        DEADLINE,
        EVENT
    }

    /**
     * Constructor for Duke main class.
     * @param filePath Relative file path to the folder containing the data file.
     * @param fileName Name of data file.
     */
    public Duke(String filePath, String fileName) {
        ui = new Ui();
        storage = new Storage(filePath, fileName);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Main method to start the Duke program.
     */
    public void run() {
        Scanner myObj = new Scanner(System.in);
        boolean exit = false;
        Parser parser = new Parser(ui, tasks, storage);

        ui.welcomeMessage();

        while (!exit) {
            String userInput = myObj.nextLine();
            exit = parser.parse(userInput);
        }
    }

    public static void main(String[] args) {
        new Duke("/data", "/data.txt").run();
    }
}
