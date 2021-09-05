package duke;

import duke.command.Command;
import duke.exceptions.DukeException;
import duke.tasks.TaskList;

public class Duke {

    /** Relative path to file used in our Duke simulations */
    public static String FILE_PATH = "data/";

    TaskList taskList;
    FileController fc;
    Parser parser;

    public Duke() {
        fc = new FileController(FILE_PATH, "list.txt");
        String contents = fc.readContentsAsString();
        taskList = new TaskList(contents);
        parser = new Parser();
    }

    /**
     * Gets String response to a particular input
     *
     * @param input Full input sent to duke
     * @return String response after parsing input
     */
    public String getResponse(String input) {
        try {
            Command c = parser.parseCommand(input);
            return c.execute(taskList, fc);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    public String getWelcome() {
        return "Hello from\n" + " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
    }


}
