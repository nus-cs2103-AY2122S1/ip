package duke.commands;

import duke.utils.*;

/**
 * Represent an bye/exit action to be executed.
 */
public class ExitCommand extends Command{
    /**
     * prints out the bye statement
     *
     * @param tasks    the tasklist
     * @param ui    the user-interface
     * @param storage Persistent storage for data
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        System.out.println("Bye. Hope to see you again soon!");
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
