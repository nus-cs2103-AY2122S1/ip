package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

import java.util.Scanner;

/**
 * Stub for Duke.
 * For testing purposes only.
 *
 * CS2103T Individual Project AY 21/22 Sem 1
 * @author Benedict Chua
 */
public class DukeStub {
    public static void main(String[] args) {
        // Initialise program
        Storage storage = new Storage();
        TaskList tasks = new TaskList(null, storage); // remove file dependency
        Parser parser = new Parser(tasks);
        Scanner sc = new Scanner(System.in);
        boolean isExit = false;

        // Greets user
        Ui.printLineSeparator();
        Ui.showWelcome();
        Ui.printLineSeparator();
        Ui.printEmptyLine();

        // Carries out commands inputted by user into the Scanner
        while (!isExit) {
            try {
                Ui.printLineSeparator();
                String input = sc.nextLine();
                Command command = parser.getCommand(input);
                command.execute();
                isExit = command.isExit();
            } catch (DukeException e) {
                Ui.displayMessage(new String[] {e.toString()});
            } finally {
                Ui.printLineSeparator();
                Ui.printEmptyLine();
            }
        }
    }
}
