package duke;

import duke.command.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;

public class Duke {
    private final String DEFAULT_FILEPATH = "data/tasks.txt";

    private final Storage STORAGE;
    private TaskList tasks;

    /**
     * Constructor for Duke.
     * No parameter constructor fallbacks to DEFAULT_FILEPATH.
     * Loads the tasks from the file.
     * If fail, start with an empty TaskList.
     */
    public Duke() {
        STORAGE = new Storage(DEFAULT_FILEPATH);
        try {
            tasks = new TaskList(STORAGE.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            String message = command.execute(this.tasks, this.STORAGE);
            this.STORAGE.writeTasksToFile(this.tasks);
            return message;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}