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
        index = input.replaceAll("[^0-9]", "");
    }

    /**
     * Deletes a task of interest from both short-term and long-term memory.
     *
     * @param tasks    the tasklist
     * @param ui    the user-interface
     * @param storage Persistent storage for data
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        try {
            tasks.deleteTask(index);
            Storage.removeLine(Integer.parseInt(index)-1);
        } catch (Exception e){
            System.out.println(e);
        }
    }
    
    @Override
    public boolean isExit(){
        return false;
    }
}


