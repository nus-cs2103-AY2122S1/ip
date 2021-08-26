package duke;

import duke.Exception.DukeException;
import duke.task.TaskList;

import java.util.Scanner;

/**
 * The class for the chat bot, Duke
 */
public class Duke {

    /** Storage object to save to and load from the data file */
    private Storage storage;
    /** List of all the Tasks */
    private TaskList tasks;
    /** Ui object to handle the UI output to the user */
    private Ui ui;

    /**
     * Constructor for Duke class.
     *
     * @param filePath The specified filepath to save and load the data file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = storage.load();
    }

    private void run() {
        //Welcome message
        ui.printWelcomeMessage();

        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser(tasks);
        boolean end = false;

        //Main functionality of duke.Duke
        while(!end) {
            String command = "";
            if(sc.hasNextLine()) {
                command = sc.nextLine().trim();
            }
            try {
                if (parser.parse(command) == 1) {
                    end = true;
                    ui.printGoodbyeMessage();
                    storage.save(tasks);
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        sc.close();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
