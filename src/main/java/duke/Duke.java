package duke;

import duke.command.Command;
import duke.task.Task;

import java.util.ArrayList;

public class Duke {
    private TaskList tasks;
    private final Ui ui;
    private final Storage storage;
    private boolean isRunning = true;

    Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(new ArrayList< Task >());
        try{
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.errorFrame(e.getMessage());
        }
    }

    public void run() {
        ui.showWelcome();
        while (isRunning) {
            String stringCommand = ui.readCommand();
            try {
            Command c = Parser.parse(stringCommand);
                c.execute(tasks, ui, storage);
                isRunning = c.isRunning();
                storage.save(tasks);
            } catch (DukeException e) {
                ui.errorFrame(e.getMessage());
            }
        }
    }

    public static void main(String[] args){
        Duke duke = new Duke(".\\src\\main\\data\\data.txt");
        duke.run();
    }
}
