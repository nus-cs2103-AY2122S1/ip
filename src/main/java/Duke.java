import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.Parser;
import duke.DukeException;

import java.io.File;

/** The main class of the application */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private File file;
    private Parser parser;
    private Ui ui;

    public Duke() {

    }

    /**
     * Constructor the Duke class
     *
     * @param filePath The file path of the file that holds the contents
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(filePath);
        } catch (DukeException e) {
            ui.showLoadingError();
            this.tasks = new TaskList(filePath);
        }
        ui = new Ui(this.tasks, this.storage);
        parser = new Parser();
        this.file = new File(filePath);
        this.tasks.readFromFile();
    }

    /**
     * Method that runs the Duke by asking for user input
     *
     * @return void
     */
    public void run() {
        System.out.println("Hello! This is Duke, your very own chat bot.");
        System.out.println("What can I help you with ?");
        while (true) {
            String userCommand = ui.input();
            if (!userCommand.equals("bye")) {
                try {
                    parser.parse(userCommand, this.tasks, this.storage);
                } catch (DukeException e) {
                    System.out.println(e.getErrorMessage());
                }
            } else {
                System.out.println("It's sad to see you go :(");
                System.out.println("Goodbye, hope to you another day!");
                break;
            }
        }
    }

    /** main method */
    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }

    /**
     * Returns the appropriate response according to user input
     *
     * @param input The input entered by the user
     * @return The output matching the input request of the user
     */
    public String getResponse(String input) {
        while (!input.equals("bye")) {
            try {
                this.tasks = new TaskList("./data/duke.txt");
            } catch (DukeException e) {
                ui.showLoadingError();
                this.tasks = new TaskList("./data/duke.txt");
            }
            this.tasks.readFromFile();
            return parser.parse(input, this.tasks, this.storage);
        }
        return "It's sad to see you go :(";
    }
}
