import duke.command.Command;

import duke.exception.DukeException;

import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;

import java.util.Scanner;

/**
 * Main Duke class
 */
public class Duke {

    private final Parser parser;
    private final TaskList taskList;
    private final Storage storage;

    /**
     * Basic Constructor
     *
     * @param filePath the path where file savedOutput.txt. which contains past state is located
     * @throws DukeException
     */
    public Duke(String filePath) throws DukeException{
        storage = new Storage(filePath);
        taskList = new TaskList();
        parser = new Parser(storage, taskList);
        parser.loadTask();
    }

    /**
     * Continuous scan loops until user input "bye"
     */
    private void  textBasedRun() {
        boolean isBye = false;
        Scanner scan = new Scanner(System.in);
        while (!isBye) {
            String input = scan.nextLine();
            try {
                Command c = parser.parse(input);
                System.out.println(c.exec());
                isBye = c.checkIsBye();
            } catch (DukeException e) {
                System.out.println(e.toString());
            }
        }
    }




    /**
     * Main program
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            new Duke("src/main/resource").textBasedRun();

        } catch (DukeException e) {
            System.out.println(e);
        }

    }
}
