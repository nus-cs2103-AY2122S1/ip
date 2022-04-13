import java.io.FileNotFoundException;
import java.io.IOException;

import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.TaskList;

public class Duke {

    private final Storage storage;
    private final TaskList taskList;
    private final Parser parser;

    /**
     * Initialises Duke program.
     */
    public Duke() {
        this.taskList = new TaskList();
        this.storage = new Storage("data/tasks.txt", taskList);
        this.parser = new Parser(taskList);
    }


    /**
     * Generates response based on user input.
     *
     * @param input User input.
     * @return Duke's response.
     */
    public String getResponse(String input) throws DukeException {

        if (input.matches("bye")) {
            try {
                storage.writeToFile(taskList.getTasks());
                return "Bye! Hope to see you again! :D";
            } catch (IOException e) {
                return "No tasks saved. Start by creating a new task.";
            }
        } else {
            try {
                String result = parser.parseCommand(input);
                return result;
            } catch (DukeException e) {
                return e.toString();
            }

        }
    }

    /**
     * Retrieves tasks saved in txt file.
     *
     * @return List of previously saved tasks.
     */
    public String getTasks() {
        try {
            return storage.printFileContents(taskList);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return "You don't have any saved tasks";
    }
}
