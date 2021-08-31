package cs2103.duke;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class encapsulates a Duke chat-bot.
 */
public class Duke {
    private static final int lv = 8;
    private static final String[] features = {
            "",
            "Greet, Echo, Exit",
            ", Add, List",
            ", Mark as Done",
            ", ToDos, Events, Deadlines",
            ", Handle Errors",
            ", Delete",
            ", Save",
            ", Dates and Times"
    };
    private static ArrayList<Task> taskArrayList = new ArrayList<>();
    private static final String dukeFilePath = "./data/duke.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String dukeFilePath) {
        ui = new Ui();
        storage = new Storage(dukeFilePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException | IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() throws IOException {
        ui.showWelcome();
        boolean canExit = false;
        // Scanner to read user inputs
        Scanner scanner = new Scanner(System.in);

        while (!canExit) {
            String userInput = scanner.next();
            try {
                if (userInput.equals("bye")) { // user inputs "bye", set canExit to true and Exit
                    canExit = true;
                    String temp = tasks.listBeautify(taskArrayList);
                    storage.overwriteFile(dukeFilePath, temp);
                    ui.showGoodbye();
                } else { // check first input
                    ui.handleInput(scanner, userInput, tasks);
                }
            } catch (DukeException | IOException e) {
                e.printStackTrace(); // print stack trace for e
            }
        }
    }

    public static void main(String[] args) throws IOException, DukeException {
        new Duke("data/duke.txt").run();
    }
}