package commands;

import java.util.*;
import tasks.Task;
import utils.*;

public class ListCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        ArrayList<Task> userInputs = tasks.getTasks();
        for (int i = 0; i < userInputs.size(); i++) {
            Task task = userInputs.get(i);
            System.out.println((i + 1) + ". " + task);
        }
    }

    @Override
    public boolean isExit(){
        return false;
    }
}




