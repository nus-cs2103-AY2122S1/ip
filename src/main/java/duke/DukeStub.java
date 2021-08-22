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
        TaskList tasksList = new TaskList(null, storage); // remove file dependency
        Parser parser = new Parser(tasksList);
        Scanner sc = new Scanner(System.in);
        boolean isExit = false;

        // Greets user
        Ui.showLine();
        Ui.showWelcome();
        Ui.showLine();
        Ui.newLine();

        // Carries out commands inputted by user into the Scanner
        while (!isExit) {
            try {
                Ui.showLine();
                String input = sc.nextLine();
                Command command = parser.getCommand(input);
                command.execute();
                isExit = command.isExit();
            } catch (DukeException e) {
                Ui.displayMessage(new String[] {e.toString()});
            } finally {
                Ui.showLine();
                Ui.newLine();
            }
        }
    }
}
