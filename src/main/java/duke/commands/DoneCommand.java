package duke.commands;

import java.util.*;

import duke.tasks.Task;
import duke.utils.*;

public class DoneCommand extends Command {
    String index;

    public DoneCommand(String input){
        index = input.replaceAll("[^0-9]", "");
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        try {
            Task task = tasks.getTask(index);
            task.markAsDone();
            Storage.updateLine(Integer.parseInt(index)-1);
            System.out.println("Nice! I've marked this task as done: ");
            System.out.println(" " + task);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public boolean isExit(){
        return false;
    }
}
