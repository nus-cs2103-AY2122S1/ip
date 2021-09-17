package duke;

import java.io.IOException;

import duke.command.Command;
import javafx.application.Platform;

public class Duke {
    private static TaskList taskList = new TaskList();
    private Storage storage;
    private ResponseFormatter responseFormatter;
    private History history;

    /**
     * Constructor for Duke object
     *
     * @param filePath Relative path to where the data was stored
     */
    public Duke(String filePath) {
        responseFormatter = new ResponseFormatter();
        storage = new Storage(filePath);
        history = new History();
        try {
            TaskList storedTasks = storage.readFile();
            taskList = storedTasks == null ? new TaskList() : new TaskList(storedTasks);
        } catch (IOException e) {
            responseFormatter.formatFileError(e);
        }
    }

    /**
     * Gets the repsonse of chatbot based on user input
     *
     * @param input User input raw
     * @return Chatbot response that has been formatted
     */
    public String getResponse(String input) {
        assert !input.isEmpty() : "input should not be empty!";
        try {
            Command command = Parser.parseCommands(input);
            boolean isExit = command.getExit();

            if (isExit) {
                Platform.exit();
            }

            return command.execute(taskList, responseFormatter, storage, history);
        } catch (IOException e) {
            return responseFormatter.formatFileError(e);
        }
    }
}
