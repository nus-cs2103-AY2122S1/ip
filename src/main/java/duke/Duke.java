package duke;

import duke.command.Command;
import duke.exception.DukeException;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private UI userInt;
    private TaskList tasks;
    private User user;
    private static Scanner sc = new Scanner(System.in);

    public Duke(User user) {
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

    public void run() {
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

    public static void shutdown() {
        Duke.sc.close();
    }

    public static void main(String[] args) {
        Duke user1 = new Duke(new User("User 1"));
        user1.run();
        Duke.shutdown();
    }
}
