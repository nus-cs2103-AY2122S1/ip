package duke.logic;

import java.io.IOException;

import duke.logic.commands.Command;
import duke.logic.commands.CommandResult;
import duke.logic.parser.Parser;
import duke.logic.tasks.TaskList;
import duke.storage.Storage;

/**
 * The logic of the app, parsing user input and performing commands accordingly.
 */
public class Duke {
    private Parser parser;
    private TaskList taskList;

    /**
     * Creates an instance of the program logic that can parse user input
     * and perform commands.
     *
     * @return a Duke instance serving as the program logic.
     */
    public static Duke init() {
        Duke d = new Duke();
        d.parser = new Parser();
        try {
            d.taskList = new TaskList(Storage.load());
        } catch (IOException e) {
            // todo Handle corrupt data
            System.out.printf("Cannot load tasks\n  %s\nCreated a new task list.", e.getMessage());
            d.taskList = new TaskList();
        }
        return d;
    }

    /**
     * Processes the user input and obtains the response.
     *
     * @param userCommandText The user input.
     * @return A string describing the program's response to the user input.
     */
    public String getResponse(String userCommandText) {
        Command command = parser.parse(userCommandText);
        command.setTaskList(taskList);
        CommandResult result = command.execute();
        return result.getMessage();
    }

    /**
     * Saves data and ends the program.
     *
     * @throws IOException If an exception occurred when writing the data.
     */
    public void end() throws IOException {
        Storage.save(taskList.getSaveFormat());
    }
}
