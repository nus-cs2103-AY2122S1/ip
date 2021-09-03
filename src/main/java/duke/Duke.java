package duke;

import java.util.Scanner;

import duke.command.ExitCommand;

import duke.command.DukeCommand;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Encapsulate a bot which helps user to keep track of a list of task.
 *
 * @author Zhi Bin
 * @version Duke Level 10
 */
public class Duke{
    private final Storage storage;
    private final TaskList taskList;
    private final Ui ui;
    private final Parser parser;
    private boolean isActive;

    /**
     * Constructor of Duke
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        taskList = new TaskList();
        parser = new Parser(taskList, storage, ui);
        isActive = true;
    }

    private void run() {
        isActive = true;
        taskList.loadFromList(storage.load());

        ui.greet();

        Scanner sc = new Scanner(System.in);

        while (isActive) {
            String input = sc.nextLine();
            try {
                DukeCommand command = parser.processInput(input);
                command.execute();
                isActive = !command.isExit();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        sc.close();
    }

    /**
     * Starts the program.
     *
     * @param args Arguments needed to start to program
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    /**
     * Currently a stub, to be replaced.
     *
     * @param input The user's input
     * @return A string containing the response to be displayed under duke
     */
    public String getResponse(String input) {
        if (isActive) {
            try {
                DukeCommand c = parser.processInput(input);
                if (c instanceof ExitCommand) {
                    isActive = false;
                }
                return c.execute();
            } catch (DukeException e) {
                return ui.showMessageGUI(e.getMessage());
            }
        }
        return "";
    }
}
