package duke.commands;
import duke.Storage;
import duke.TaskList;
import duke.tasks.Task;
import duke.Ui;
import duke.exceptions.NoTaskMatchException;
import duke.exceptions.WrongFindFormatException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class that specifies the properties
 * of a find command.
 */
public class FindCommand extends Command {

    /**
     * Calls parent class to initialise the
     * find command with description "find"
     * followed by user description.
     * @param desc String description of
     * find.
     */
    public FindCommand(String desc) {
        super(desc);
    }

    /**
     * Indicates whether the program should
     * stop running, and in this is case no.
     * @return boolean that this command
     * should exit the application or not.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(
        TaskList tasks, Ui ui,
        Storage storage) throws WrongFindFormatException,
        NoTaskMatchException {
        String[] instructions = this.commandDescription.split(" ");
        if (instructions.length <= 1) {
            throw new WrongFindFormatException();
        }
        String toFind = String.join(" ",
                Arrays.copyOfRange(instructions,
                        1,
                        instructions.length));
        String displayString = this.convertMatchingTaskToStringDisplay(
            toFind, tasks);
        if (displayString.length() == 0) {
            throw new NoTaskMatchException();
        }
        return ui.displayFoundTasks(displayString);
    }

    private String convertMatchingTaskToStringDisplay(
        String searchString, TaskList tasks) {
        StringBuilder sb = new StringBuilder("");
        ArrayList<Task> taskItems = tasks.getTaskList();
        int counter = 1;
        for (int i = 1; i <= tasks.getTaskListLength(); i++) {
            Task task = taskItems.get(i - 1);
            String taskString = task.toString();
            if (taskString.contains(searchString) && counter == 1) {
                sb.append(counter + ". " + taskString);
                counter++;
            } else if (taskString.contains(searchString) && counter != 1) {
                sb.append("\n" + counter + ". " + taskString);
                counter++;
            }
        }
        return sb.toString();
    }
}
