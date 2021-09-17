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
     * Returns a response based on the input command. The response is an array of String with two elements:
     * <ul>
     *     <li>The first element is the corresponding response of the input that will be displayed on the GUI;</li>
     *     <li>The second element is either {@code T} or {@code F}. It indicates whether the input is a valid command.
     *     </li>
     * </ul>
     *
     * @param input The input command.
     * @return response An array of String described above.
     */
    public String[] getResponse(String input) {
        try {
            Command command = Parser.parseCommand(input);
            if (command.isExit()) {
                Platform.exit();
            }
            return new String[] {command.execute(this.tasks, this.storage), "T"};
        } catch (DukeException e) {
            return new String[] {e.getMessage(), "F"};
        }
    }
}
