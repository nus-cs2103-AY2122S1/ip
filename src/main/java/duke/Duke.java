package duke;

import duke.command.Command;
import duke.task.TaskList;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    private boolean exit;

    /**
     * Constructor for a Duke instance.
     * @param filePath file path for file to save/load save data.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.exit = false;
        try {
            taskList = storage.load();
        } catch (FileNotFoundException e) {
            taskList = new TaskList();
        }
    }

    /**
     * Runs the Duke instance by taking user input and running it through Parser.
     * Will loop continuously until user enters the exit command.
     */
    public void run() {
        Parser parser = new Parser(this, taskList, storage, ui);
        ui.showWelcome();
        Scanner sc = new Scanner(System.in);

        while (!exit) {
//            String userInput = ui.takeInput();
            if (sc.hasNextLine()) {
                Command cmd = parser.parse(sc.nextLine());
                if (cmd != null) {
                    cmd.execute();
                }
            }
        }

        sc.close();

    }

    /**
     * Triggers the exit boolean to stop the Duke from running.
     */
    public void triggerExit() {
        exit = true;
    }

    /**
     * Main function.
     * @param args
     */
    public static void main(String[] args) {
        new Duke("task-list.txt").run();
    }
}
