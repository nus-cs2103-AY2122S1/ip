package duke;

import duke.commands.Command;
import duke.exceptions.UserInputError;
import duke.tasks.TaskList;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;

/**
 * The Duke class that runs the Duke program.
 */
public class Duke {

    public final TaskList taskList = new TaskList(Storage.readDatabase());
    private final Ui ui = new Ui(taskList);

    /**
     * Return a String representing Duke's response to user's input.
     * If user input is invalid, an error message String is returned.
     *
     * @param input User input String.
     * @return String of Duke's response.
     */
    public String getResponse(String input) {
        Parser parser = new Parser(input, taskList);
        String output;

        try {
            Command command = parser.parse();
            output = command.execute(taskList, ui);
        } catch (UserInputError e) {
            output = e.getMessage();
        }

        assert !output.equals("") : "empty output";
        return ui.formatOutput(output);
    }
}
