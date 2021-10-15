package duke.commands;
import duke.utils.Storage;
import duke.utils.TaskList;


/**
 * Represent a deletion action to be executed.
 */
public class DeleteCommand extends Command{
    Integer index;

    public DeleteCommand(String input){
        Integer parsedIndex = Integer.parseInt(input.replaceAll("[^0-9]", ""));
        index = parsedIndex-1;
        assert(index>=0);
    }

    /**
     * Deletes a task of interest from both short-term and long-term memory.
     *
     * @param tasks    the tasklist
     * @param storage Persistent storage for data
     */
    @Override
    public String execute(TaskList tasks, Storage storage){
        try {
            tasks.deleteTask(index);
            Storage.removeLine(index);
            return "Deleted!";
        } catch (Exception e){
            System.out.println(e);
            return e.getMessage();
        }
    }
    
    @Override
    public boolean isExit(){
        return false;
    }
}


