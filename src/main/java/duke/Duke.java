package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;

import java.time.format.DateTimeParseException;

public class Duke {
    public enum TaskType {
        TODO, DEADLINE, EVENT
    }
    
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath, String path) {
        ui = new Ui();
        storage = new Storage(filePath, path);
        tasks = new TaskList(storage.load());
    }

    public void run() {
        ui.showGreeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = ui.getIsExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (DateTimeParseException e) {
                System.out.println("OOPS! Please input date in this format: yyyy-mm-dd");
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt", "data/").run();
    }
}



