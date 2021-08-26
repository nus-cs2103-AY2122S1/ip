import java.io.FileNotFoundException;
import java.io.IOException;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Duke {
    private final Ui ui;
    private final Storage storage;
    private TaskList tasks;

    public Duke(Path filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasksFromFile());
        } catch (FileNotFoundException e) {
            ui.showFileNotFoundError();
            tasks = new TaskList();
        } catch (Exception e) {
            ui.showLoadingError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showIntroduction();
        boolean isTerminated = false;
        while (!isTerminated) {
            try {
                String input = ui.readInput();
                ui.showLine();
                Command command = Parser.parseCommandFromInput(input);
                tasks = command.execute(tasks, ui, storage);
                isTerminated = command.isTerminated();
            } catch (IOException e) {
                ui.showError("The data failed to save to the save file with error:"
                        + e.getMessage());
            } catch (IllegalArgumentException e) {
                // When invalid command is given, it is unable to be parsed into the enum
                ui.showError("You have entered an invalid command.");
            } catch (Exception e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        Path filePath = Paths.get(System.getProperty("user.dir"), "data", "tasks.txt");
        new Duke(filePath).run();
    }
}
