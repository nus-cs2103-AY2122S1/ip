package duke;

import java.util.Scanner;
import duke.command.Command;
import duke.exception.DukeException;

/**
 * Main class of project.
 */
public class Duke {
    private Storage storage;
    private UI userInt;
    private TaskList tasks;
    private User user;
    private static Scanner sc = new Scanner(System.in);

    /**
     * Constructor of Duke instance, which initalises the other
     * components of the project.
     *
     * @param user User object which encapsulates the users' name and visit history
     */
    private Duke(User user) {
        this.user = user;
        this.storage = new Storage();
        this.userInt = new UI();
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            userInt.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    /**
     * Main method which starts up the Duii Bot,
     * parsing input and executing the respective behaviours.
     */
    private void run() {
        userInt.greet(this.user);
        userInt.printPrevSession(this.tasks, this.user);
        userInt.showLine();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = userInt.readCommand(Duke.sc);
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, userInt, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                userInt.showError(e.getMessage());
            } finally {
                userInt.showLine();
            }
        }
        System.out.println("Session Terminated.");
    }

    /**
     * Fully terminates the bot to prevent any inputs from any user.
     */
    public static void shutdown() {
        Duke.sc.close();
    }

    /**
     * Main method of the project.
     *
     * @param args Command Line arguments.
     */
    public static void main(String[] args) {
        Duke user1 = new Duke(new User("User 1"));
        user1.run();
        Duke.shutdown();
    }
}
