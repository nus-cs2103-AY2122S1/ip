import java.io.IOException;

public class Duke {
    private final TaskList tasks;
    private final String pathName = "./data/duke.txt";
    private final Storage storage;
    private final Ui ui;

    /**
     * A private constructor to initialize variables.
     */
    private Duke() throws IOException, DukeException {
        storage = new Storage(pathName);
        ui = new Ui();

        tasks = new TaskList(storage.loadData());
    }

    /**
     * Run duke.
     *
     * @throws DukeException Error loading file.
     * @throws IOException   File exists but cannot be created or opened.
     */
    public void run() {
        String input;

        while (!(input = ui.getCommand()).equals("bye")) {
            try {
                String message = Parser.interpretCommand(input).execute(tasks);
                ui.print(message);
                storage.saveData(tasks.getTasks());
            } catch (DukeException | IOException e) {
                ui.print(e.getMessage());
            }
        }
        ui.close();
    }

    public static void main(String[] args) throws IOException, DukeException {
        Duke duke = new Duke();
        duke.run();
    }
}