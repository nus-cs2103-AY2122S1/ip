import exception.DukeException;
import service.ChatBot;
import service.Parser;
import service.TaskManager;
import utils.Command;

import java.util.Scanner;

/**
 * Duke class.
 *
 * This class holds the main logic for Duke project (CS2103T's iP).
 * Duke is a task manager for users that love the command line interface.
 * @author muhammad-khair, damithc
 */
public class Duke {

    private final Parser parser = new Parser();

    private final TaskManager taskManager = new TaskManager();

    private final ChatBot chatBot = new ChatBot();

    /**
     * Driver function for main logic.
     *
     * @param args command line arguments fed
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    /**
     * Runs the whole Duke process. Consists of (1) scanning input and parsing it to a
     * Command, (2) parsing the inputs in the format required by the Command and (3)
     * executing the Commanding and returning a String output to be printed to console.
     */
    public void run() {
        chatBot.greet();
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine().trim();
        String output;
        Command command = Command.parseFromFirstWord(userInput);

        while (!command.equals(Command.BYE)) {
            try {
                output = parser.execute(command, userInput, taskManager);
                chatBot.info(output);

            } catch (DukeException exception) {
                chatBot.error(exception.getLocalizedMessage());

            } finally {
                userInput = scanner.nextLine().trim();
                command = Command.parseFromFirstWord(userInput);
            }
        }
        scanner.close();
        chatBot.exit();
    }
}
