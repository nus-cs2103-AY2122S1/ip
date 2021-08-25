package duke.commands;

import java.util.*;

import duke.tasks.Task;
import duke.utils.*;

public class DeleteCommand extends Command{
    String index;

    public DeleteCommand(String input){
        index = input.replaceAll("[^0-9]", "");
    }

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


