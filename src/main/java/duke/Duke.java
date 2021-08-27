package duke;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Implements a ChatBot named Duke that helps to create and maintain a task list.
 */
public class Duke {
    private final static ArrayList<Task> list = new ArrayList<>();
    private final static Scanner scanner = new Scanner(System.in);
    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;

    /**
     * Creates Duke ChatBot.
     */
    public Duke() {
        storage = new Storage("data/TaskList.txt");
        ui = new Ui();
        try {
            tasks = new TaskList(storage.loadTask());
        } catch (DukeException e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        }
    }

    /**
     * Starts up Duke and reads user inputs for commands.
     */
    public void run() {
        ui.welcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            ui.showLine();
            isExit = Parser.parse(fullCommand, ui, storage, tasks);
            ui.showLine();
        }
        scanner.close();
    }

    /**
     * Runs Duke.
     * @param args Null
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
