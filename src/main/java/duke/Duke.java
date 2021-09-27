package duke;

import duke.commands.Command;
import duke.gui.Main;
import javafx.application.Application;

/**
 * This program is Duke bot who keeps track of tasks that user inputs
 * and let user manage the tasks through various commands.
 */
public class Duke {

    private Storage storage;
    private TaskList taskList;

    /**
     * This is a constructor for Duke bot which is necessary to run Duke
     * after putting in file path to store tasks into Hard Disk.
     *
     * @param filePath Duke stores a text file of tasks input and loads them at the given file path.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        taskList = new TaskList(storage);
    }

    /**
     * This method takes in the user input as a String and returns Duke output.
     *
     * @param input String taken from user input.
     * @return String which Duke shows the user.
     */
    public String getResponse(String input) {
        String dukeResponse = "";
        try {
            Command c = Parser.parse(input, taskList);
            dukeResponse = c.execute(taskList);
        } catch (DukeException e) {
            return e.getMessage();
        }
        return dukeResponse;
    }

    /**
     * This is the main method to run Duke.
     *
     * @param args Main method arguments.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
