package duke.commands;

import java.util.*;

import duke.tasks.Task;
import duke.utils.*;

/**
 * Represent an deletion action to be executed.
 */
public class DeleteCommand extends Command{
    String index;

    public DeleteCommand(String input){
        String inputString = input.replaceAll("[^0-9]", "");
        assert (Integer.parseInt(inputString) > 0);
    }

    /**
     * Deletes a task of interest from both short-term and long-term memory.
     *
     * @param tasks    the tasklist
     * @param ui    the user-interface
     * @param storage Persistent storage for data
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage){
        try {
            tasks.deleteTask(index);
            Storage.removeLine(Integer.parseInt(index)-1);
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


