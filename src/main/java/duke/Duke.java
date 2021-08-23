package duke;

import java.util.Scanner;
import duke.tasktypes.TaskList;
import duke.commands.Command;

/**
 * Represents the chat bot.
 */
public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Starts the Dory chat bot.
     */
    public void run() {
        ui.showIntro();

        Scanner input = new Scanner(System.in);
        while (input.hasNextLine()) {
            try {
                String beforeEdit = input.nextLine();
                String nextInput = beforeEdit.toLowerCase();
                ui.endOfCommand();
                Command c = Parser.parse(nextInput);
                c.execute(taskList, ui, storage);
                storage.updateHardDisk(taskList);
            } catch (Exception e) {
                ui.showError(e);
            } finally {
                ui.endOfResponse();
            }
        }
    }

    /**
     * Constructor for the class.
     * @param filePath filePath for the data file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList(storage.load());
    }

    public static void main(String[] args) {
        // start running the chat bot
        new Duke("data/dory.txt").run();
    }

}

