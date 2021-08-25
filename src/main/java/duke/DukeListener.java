package duke;

import duke.command.Command;
import duke.command.CommandExit;
import task.TaskList;

import java.util.Scanner;

/**
 * Waits for and processes a User Input
 */
public class DukeListener {
    private final Scanner sc;
    private final DukeParser parser;

    /**
     * Constructor for Listener that takes instantiates a Scanner and a Parser object
     *
     * @param parser used for parsing commands
     */
    public DukeListener(DukeParser parser) {
        sc = new Scanner(System.in);
        this.parser = parser;
    }

    /**
     * Waits for user input.
     * Transfers input to the parser, or terminates the listening.
     */
    public void startListen() {
        while(true) {
            // Receive Input text
            String input = sc.nextLine();
            System.out.println(Ui.LINE);

            // TaskList related inputs
            Command cmd = parser.parseInput(input);

            if (cmd instanceof CommandExit) {
                break;
            } else {
                cmd.execute();
            }

            System.out.println(Ui.LINE);
        }

        sc.close();
    }
}
