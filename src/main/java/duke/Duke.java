package duke;

import java.io.IOException;
import java.util.Scanner;

import duke.commands.Command;

/**
 * Represents the chatbot Duke.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke class.
     *
     * @throws IOException when an IO operation fails.
     */
    public Duke() throws IOException {
        this.tasks = new TaskList();
        this.ui = new Ui(new Scanner(System.in));
    }

    /**
     * Method used to run the program.
     */
    public void run() {
        Parser parser = new Parser();
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String commandLine = ui.readCommand();
                Command c = parser.getCommand(commandLine, tasks);
                c.execute(tasks);
                isExit = c.isExit();
            } catch (DukeException | IOException e) {
                System.out.println("***WARNING*** An error has occurred Master Wayne: " + e.getMessage());
            }
        }
    }

    /**
     * The main method of Duke.
     *
     * @param args
     * @throws IOException when an IO operations fails.
     */
    public static void main(String[] args) throws IOException {
        new Duke().run();
    }
}
