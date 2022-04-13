package duke.commands;
import duke.utils.Storage;
import duke.utils.TaskList;

/**
 * Represent an bye/exit action to be executed.
 */
public class ExitCommand extends Command{
    /**
     * prints out the bye statement
     *
     * @param tasks    the tasklist
     * @param storage Persistent storage for data
     */
    @Override
    public String execute(TaskList tasks, Storage storage){
        return "Bye. Hope to see you again soon! (You an close the window now)";
    }
    /**
     * Returns true so that main program can exit the loop.
     *
     * @return returns true
     */
    @Override
    public boolean isExit(){
        return true;
    }
}
