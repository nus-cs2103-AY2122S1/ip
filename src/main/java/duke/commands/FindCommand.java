package duke.commands;
import duke.Storage;
import duke.TaskList;
import duke.tasks.Task;
import duke.Ui;
import duke.exceptions.NoTaskMatchException;
import duke.exceptions.WrongFindFormatException;
import java.util.ArrayList;

public class FindCommand extends Command {
    
    public FindCommand(String desc) {
        super(desc);
    }

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
        if (instructions.length != 2) {
            throw new WrongFindFormatException();
        }

        String toFind = instructions[1];
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
            if (taskString.contains(searchString)) {
                if (counter == 1) {
                    sb.append(counter + ". " + taskString);
                } else {
                    sb.append("\n" + counter + ". " + taskString);
                }
                counter++;
            }
        }
        return sb.toString();
    }
}
