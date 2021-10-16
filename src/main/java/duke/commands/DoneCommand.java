package duke.commands;
import duke.tasks.Task;
import duke.utils.Storage;
import duke.utils.TaskList;
import java.io.IOException;


/**
 * Represent a Done action to be executed.
 */
public class DoneCommand extends Command {
    Integer index;


    public DoneCommand(String input) {
        Integer parsedIndex = Integer.parseInt(input.replaceAll("[^0-9]", ""));
        index = parsedIndex-1;
        assert(index>=0);
    }

    /**
     * Marks the task of interest as completed
     *
     * @param tasks    the taskList
     * @param storage Persistent storage for data
     */
    @Override
    public String execute(TaskList tasks, Storage storage){
        try {
            Task task = tasks.getTask(index);
            task.markAsDone();
            Storage.updateLine(index);
            return "Nice! I've marked this task as done: " + task;
        } catch (IOException e) {
            return e.getMessage();
        } catch (IndexOutOfBoundsException e) {
            return "Hey it does not exists!";
        }
    }

    @Override
    public boolean isExit(){
        return false;
    }
}
