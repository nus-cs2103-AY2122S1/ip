import java.util.Scanner;

public class Duke {

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    public Duke(String storageFilePath) {
         storage = new Storage(storageFilePath);
         tasks = new TaskList(storage);
         ui = new Ui();
    }

    public void run() {

        Scanner scanner = new Scanner((System.in));

        ui.greet();

        boolean exit = false;
        while (!exit) {
            String userInput = scanner.nextLine().strip();
            try {
                Command command = Parser.parse(userInput);
                exit = command.execute(tasks, ui);
            } catch (DukeException e) {
                // Get Duke to say out the error
                ui.notifyException(e);
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke("data/tasks.txt");
        duke.run();
    }
}
