public class Duke {

    private final Storage storage;
    private final Parser parser;
    private TaskList tasks;
    private final Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = storage.retrieveTaskList();
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.greet();
        boolean isExit = false;
        do {
            try {
                String input = ui.readLine();
                Command command = parser.parseCommand(input);
                command.execute(tasks, ui);
                storage.storeTaskList(tasks);  // save the latest task list no matter what command it is
                isExit = ExitCommand.isExit(command);
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        } while (!isExit);
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

}
