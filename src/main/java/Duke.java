import duke.TaskList;
import duke.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Initialises Duke program.
     *
     * @param filepath Filepath of text file to read and write data.
     */
    public Duke(String filepath) {
        this.ui = new Ui();
        this.taskList = new TaskList();
        this.storage = new Storage(filepath, taskList);
    }

    /**
     * Runs the program until termination.
     */
    public void run() {
        ui.showWelcomeMessage();

        System.out.println("You've saved the following tasks last time we met:");
        try {
            storage.printFileContents();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        while (true) {
            String command = ui.getUserCommand();

            if (command.matches("bye")) {
                try {
                    storage.writeToFile(taskList.tasks);
                    ui.showFarewellMessage();
                } catch (IOException e) {
                    System.out.println("No tasks saved.");
                }
            } else {
                Parser parser = new Parser(taskList);
                parser.parseCommand(command);
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

}
