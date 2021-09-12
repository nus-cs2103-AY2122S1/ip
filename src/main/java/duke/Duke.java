package duke;

import duke.commands.Command;
import duke.tasktypes.TaskList;
import java.util.Scanner;

/**
 * Represents the chat bot.
 */
public class Duke {

    private final Storage storage;
    private final TaskList taskList;
    private final Ui ui;

    /**
     * Constructor for the class.
     *
     * @param filePath filePath for the data file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        assert !filePath.equals("");
        storage = new Storage(filePath);
        taskList = new TaskList(storage.load());
    }

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
     * Generates response for user's input.
     *
     * @param input user's input typed in console.
     * @return Returns response back to user.
     */
    public String getResponse(String input) {
        Command c = Parser.parse(input);
        return c.execute(taskList, ui, storage);
    }

    /**
     * Starts the Dory program.
     *
     * @param args arguments.
     */
    public static void main(String[] args) {
        // start running the chat bot
        new Duke("data/dory.txt").run();
    }
}
