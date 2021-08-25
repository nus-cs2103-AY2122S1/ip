package duke;

import duke.command.Command;

import java.time.format.DateTimeFormatter;

public class Duke {

    private static final String LOCAL_STORAGE_LOCATION = "src/Data/LocalStorage.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private static final DateTimeFormatter FORMAT_FROM_LOCAL_STORAGE = DateTimeFormatter.ofPattern("dd LLLL yyyy");

    /**
     * Constructor for class Duke
     *
     * @param filePath path of local storage file
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Returns the formatter used for local storage
     *
     * @return formatter used for local storage
     */
    public static DateTimeFormatter getFormatter() {
        return FORMAT_FROM_LOCAL_STORAGE;
    }

    /**
     * Runs Duke until bye is input by user
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.printReplyOpening(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.reply(e.getMessage());
            } finally {
                ui.printReplyClosing();
            }
        }
    }

    /**
     * Main function to start Duke
     *
     * @param args
     */
    public static void main(String[] args) {
        new Duke(LOCAL_STORAGE_LOCATION).run();
    }

    /**
     * Returns location of local storage
     *
     * @return location of local storage
     */
    public static String getLocalStorageLocation() {
        return LOCAL_STORAGE_LOCATION;
    }
}