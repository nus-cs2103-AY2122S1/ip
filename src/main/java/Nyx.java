import nyx.Ui;
import nyx.Parser;
import nyx.Storage;
import nyx.TaskList;
import nyx.NyxException;

/**
 * Entry point of the Nyx chatbot application.
 */
public class Nyx {
    private static final String logo = " __      _\n"
            + "|   \\   | |__    __ __     __\n"
            + "| |\\ \\  | |\\ \\  / / \\ \\   / /\n"
            + "| | \\ \\ | | \\ \\/ /   \\ \\ / / \n"
            + "| |  \\ \\| |  \\  /    /    /\n"
            + "| |   \\   |  / /    / / \\ \\\n"
            + "|_|    \\__| /_/    /_/   \\_\\\n";
    private final Ui ui;
    private TaskList taskList;
    private final Storage storage;

    /**
     * Constructs a Nyx object to initialize the chatbot with the specified folder and file names.
     * @param folderName Name of the folder where the file is.
     * @param fileName Name of the file to store the tasks.
     */
    public Nyx(String folderName, String fileName) {
        ui = new Ui();
        this.storage = new Storage(folderName, fileName);
        try {
            taskList = new TaskList(storage.loadData());
        } catch (NyxException e) {
            ui.displayOutput(e.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * Begins interactions with the user until a bye command is encountered.
     */
    public void run() {
        ui.displayStart(logo);

        String input;

        do {
            input = ui.readInput();
            try {
                String output = Parser.parse(input, taskList, storage);
                ui.displayOutput(output);
            } catch (NyxException e) {
                ui.displayOutput(e.getMessage());
            }

        } while (!input.equals("bye"));
    }

    /**
     * Creates a Nyx object to initialize the chatbot and run it.
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        new Nyx("data", "nyx.txt").run();
    }
}