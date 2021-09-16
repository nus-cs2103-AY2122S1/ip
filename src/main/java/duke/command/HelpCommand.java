package duke.command;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.stream.Collectors;

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
        String path = "app_data/commands.txt";
        ArrayList<String> commandList;

        try {
            Path filePath = java.nio.file.Paths.get(path);
            // read command from file
            // example:
            // event <TASK_DESCRIPTION> /at <TIME> | add an event task which occurs at some time
            commandList = Files.lines(filePath).map(line -> {
                String[] command = line.split(" \\| ", 2);
                String res = String.format("%-40s\t%-40s", command[0], command[1]);
                System.out.println(res);
                return res;
            }).collect(Collectors.toCollection(ArrayList::new));
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
