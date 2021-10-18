package duke.commands;
import duke.utils.DukeException;
import duke.utils.Storage;
import duke.utils.TaskList;
import java.io.IOException;


/**
 * Represent a deletion action to be executed.
 */
public class DeleteCommand extends Command{
    Integer index;

    public DeleteCommand(String input) throws DukeException {
        try {
            Integer integerIndex = Integer.parseInt(input.replaceAll("[^0-9]", ""));
            index = integerIndex-1;
        } catch (NumberFormatException e) {
            throw new DukeException("Hey please check that your input has a valid format!");
        }
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
        } catch (IndexOutOfBoundsException e) {
            return "Hey it does not exists!";
        } catch (IOException e) {
            return "hey it does not exists!";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Please make sure you're inputing the correct format!";
        }
    }
    
    @Override
    public boolean isExit(){
        return false;
    }
}


