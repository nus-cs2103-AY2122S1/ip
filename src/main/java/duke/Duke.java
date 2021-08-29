package duke;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import duke.commands.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents the initialization of Duke application.
 * Initializes the application and starts the interaction with the user.
 */
public class Duke {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    /**
     * @param filePath to read/write task list data
     * @throws IOException
     */
    public Duke(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main Duke program.
     *
     */
    public void run() {
        boolean isActive = true;

        String welcomeMsg = "Hey, I'm duke.Duke.\n"
                + "What's up?\n";
        String exitMsg = "Bye! Hope I helped!\n"
                + "See you next time :)\n";

        System.out.println(welcomeMsg);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        Scanner sc = new Scanner(System.in);

        while (isActive) {
            String input = sc.nextLine();
            try {
                //String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(input);
                c.execute(tasks, ui, storage);
                isActive = c.isActive();

            } catch (DukeException e) {
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println(e.getMessage() + "\n");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            } catch (DateTimeParseException e) {
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("Please enter the date and time "
                        + "in dd/mm/yyyy hh:mma format! :\n)");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            }
        }

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(exitMsg);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
}
