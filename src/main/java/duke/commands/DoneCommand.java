package duke.commands;

import java.util.*;

import duke.tasks.Task;
import duke.utils.*;

/**
 * Represent an Done action to be executed.
 */
public class DoneCommand extends Command {
    String index;

    public DoneCommand(String input){
        index = input.replaceAll("[^0-9]", "");
    }

    /**
     * Marks the task of interest as completed
     *
     * @param tasks    the tasklist
     * @param ui    the user-interface
     * @param storage Persistent storage for data
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage){
        try {
            Task task = tasks.getTask(index);
            task.markAsDone();
            Storage.updateLine(Integer.parseInt(index)-1);
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
