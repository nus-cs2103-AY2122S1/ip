package Duke;

import Duke.Commands.Command;
import Duke.Storage.FileFormatException;
import Duke.Storage.Storage;
import Duke.Storage.TaskStorage;
import Duke.Task.Task;
import Duke.Task.TaskList;
import Duke.Ui.Parser;
import Duke.Ui.Ui;
import Duke.Ui.UserInput;

import java.io.IOException;

public class Duke {
    private static final String DEFAULT_TODO_STORAGE_PATH = "data/duke.txt";

    private boolean stopped = false;
    private final TaskList taskList;

    public Duke(String todoStoragePath) throws IOException {
        Storage<Task> taskStorage = new TaskStorage(todoStoragePath);
        try {
            this.taskList = new TaskList(taskStorage);
        } catch (FileFormatException e) {
            Ui.print(Ui.FILE_FORMAT_ERROR_MESSAGE);
            throw e;
        }
    }

    public Duke() throws IOException {
        this(DEFAULT_TODO_STORAGE_PATH);
    }

    public void run() {
        Ui.print(Ui.GREETING_MESSAGE);
        while (!this.stopped) {
            UserInput input = Parser.parse(Ui.read());
            try {
                Command.matching(input).run(this, input);
            } catch (DukeException e) {
                Ui.print(String.format(Ui.ERROR_MESSAGE, e.getMessage()));
            }
        }
        Ui.print(Ui.EXIT_MESSAGE);
    }

    public void stop() {
        this.stopped = true;
    }

    public TaskList getTaskList() {
        return this.taskList;
    }
}
