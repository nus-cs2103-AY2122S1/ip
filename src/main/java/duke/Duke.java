package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.command.Command;
import duke.task.Task;
import duke.task.TaskList;

public class Duke {

    private String filePath;
    private Storage storage;
    private TaskList tasks;
    private ResponseLogic responseLogic;

    /**
     * The main Duke class that will be run.
     *
     * @param filePath The filepath of the saved file
     */
    public Duke(String filePath) {
        this.filePath = filePath;
        responseLogic = new ResponseLogic();
        storage = new Storage(filePath);
    }

    public String initializeTaskList() {
        try {
            tasks = new TaskList(storage.load());
            return responseLogic.welcomeResponse();
        } catch (FileNotFoundException e) {
            new File("./data").mkdirs();
            try {
                File file = new File("./data/task_list.txt");
                file.createNewFile();
                tasks = new TaskList(new ArrayList<Task>());
            } catch (IOException ee) {}

            return responseLogic.loadingErrorResponse(filePath);
        }
    }

    /**
     * Returns the chatbot's response to the user's input.
     *
     * @param input The user's input.
     * @return The chatbot's response.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(this.tasks, this.responseLogic, this.storage);
        } catch (DukeException e) {
            return responseLogic.dukeExceptionResponse(e);
        } catch (IndexOutOfBoundsException e) {
            return responseLogic.indexOutOfBoundsExceptionResponse();
        } catch (NumberFormatException e) {
            return responseLogic.numberFormatExceptionResponse();
        } catch (FileNotFoundException e) {
            return responseLogic.loadingErrorResponse(filePath);
        } catch (DateTimeParseException e) {
            return responseLogic.dateTimeParseExceptionResponse();
        }
    }
}
