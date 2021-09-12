package duke;

import duke.commands.Command;

/**
 * The Duke class that runs the Duke program.
 */
public class Duke {
    private static TaskList list;
    private static FileManager fm;
    private static boolean hasError = false;

    /**
     * Constructor for Duke.
     */
    public Duke() {
        fm = new FileManager();
        try {
            list = fm.getListFromFile();
        } catch (DukeException e) {
            list = new TaskList();
        }
    }

    /**
     * A method that indicates whether duke has responded with an error message.
     *
     * @return Boolean indicating whether response is an error message.
     */
    public boolean hasError() {
        return hasError;
    }

    /**
     * A method that gets the response from Duke to be displayed in the GUI.
     *
     * @param input User input to be responded to.
     * @return Response to be displayed.
     */
    public String getResponse(String input) {
        try {
            Parser parser = new Parser(input);
            assert list instanceof TaskList : "Task list cannot be found";
            Command command = parser.parse();
            String response = command.execute(list);
            fm.writeToFile(list);
            hasError = false;
            return response;
        } catch (DukeException e) {
            hasError = true;
            return e.getMessage();
        }
    }
}
