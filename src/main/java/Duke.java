import java.time.format.DateTimeFormatter;

public class Duke {

    private static final String LOCAL_STORAGE_LOCATION = "src/Data/LocalStorage.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private static final DateTimeFormatter FORMAT_FROM_LOCAL_STORAGE = DateTimeFormatter.ofPattern("dd LLLL yyyy");

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

    public static DateTimeFormatter getFormatter() {
        return FORMAT_FROM_LOCAL_STORAGE;
    }

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

    public static void main(String[] args) {
        new Duke(LOCAL_STORAGE_LOCATION).run();
    }

    public static String getLocalStorageLocation() {
        return LOCAL_STORAGE_LOCATION;
    }
}