package duke.commands;
import duke.tasks.Task;
import duke.utils.Storage;
import duke.utils.Ui;
import duke.utils.TaskList;


/**
 * Represent an Done action to be executed.
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
     * @param ui    the user-interface
     * @param storage Persistent storage for data
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage){
        try {
            Task task = tasks.getTask(index);
            task.markAsDone();
            Storage.updateLine(index);
            return "Nice! I've marked this task as done: " + task;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public boolean isExit(){
        return false;
    }
}
