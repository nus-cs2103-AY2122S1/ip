package duke;

/**
 * Represents Duke, an interactive personal assistant bot that can keep track of tasks via text commands.
 *
 * @author Hanif Kamal
 */
public class Duke {
    private final Storage storage;
    private final TaskList list;
    private final Ui ui;

    /**
     * Class constructor to initialize a Duke instance.
     *
     * @param filePath A String representation of the location of a text file as a file path. Duke loads task data from
     *                 and saves task data to this text file.
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.list = new TaskList();
        this.ui = new Ui();
    }

    /**
     * Starts up Duke to get ready for chatting and task-tracking.
     */
    public void begin() {
        try {
            storage.readTasks(list);
        } catch (Exception e) {
            System.out.println("Could not read the data file: " + e.getMessage());
        }

        ui.welcome();
        String input = ui.getNextLine();
        Parser parser = new Parser(list);
        while (!input.equals("bye")) {
            try {
                parser.parse(input);
                storage.writeTasks(list);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            input = ui.getNextLine();
        }
        ui.bye();
    }

    /**
     * Main method to begin Duke.
     * @param args Not used.
     */
    public static void main(String[] args) {
        Duke duke = new Duke("./data/duke.txt");
        duke.begin();
    }
}
