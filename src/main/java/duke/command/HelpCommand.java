package duke.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import duke.DukeException;
import duke.task.Storage;
import duke.task.TaskList;

/**
 * Represents a Command that displays tasks in the TaskList.
 */
public class HelpCommand extends Command {

    /**
     * Executes the help command.
     *
     * @param tasks   The task list to execute the command on.
     * @param storage The storage for the tasks.
     * @return a string output.
     * @throws DukeException
     */
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        // show command list
        ArrayList<String> commandList = new ArrayList<>();

        try {
            // read command from file
            // example:
            // event <TASK_DESCRIPTION> /at <TIME> | add an event task which occurs at some time
            InputStream in = this.getClass().getResourceAsStream("/app_data/commands.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            while (reader.ready()) {
                String line = reader.readLine();
                String[] command = line.split(" \\| ", 2);
                String res = String.format("%-40s\t%-40s", command[0], command[1]);
                commandList.add(res);
            }
        } catch (IOException e) {
            throw new DukeException("Could not retrieve command list!");
        }
        // help commands should appear as monospace font
        return formatCodeBlockOutput(formatOutput("Commands:", formatOutput(commandList)));
    }

    /**
     * Returns false to continue the program.
     *
     * @return false.
     */
    public boolean isExit() {
        return false;
    }
}
