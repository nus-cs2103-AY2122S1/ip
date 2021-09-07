package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.javafx.Main;
import javafx.application.Application;
import javafx.application.Platform;

/**
 * A class that encapsulates Duke, a task management bot.
 */
public class Duke {
    /**
     * Where the task in stored.
     */
    private final Storage storage;

    /**
     * A task list that store the tasks.
     */
    private final TaskList tasks;

    /**
     * Constructs a Duke bot that save its tasks in the {@code tasks.txt} file.
     */
    public Duke() {
        this.storage = new Storage("tasks.txt");
        this.tasks = this.storage.parseToTaskList();
    }

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parseCommand(input);
            if (command.isExit()) {
                Platform.exit();
            }
            return command.execute(this.tasks, this.storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
