package duke;

/**
 * 
 * Command to set a task as completed.
 */
public class DoneCommand extends Command{
    
    private int index;
    
    DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks) throws DukeException {
        if (index >= tasks.size() || index < 0) {
            throw new DukeException("Invalid value!");
        } else {
            Task taskRef = tasks.get(index);
            taskRef.setDone();
            System.out.println("Nice! I've marked this task as done:\n" + taskRef);
        }
    }
}
