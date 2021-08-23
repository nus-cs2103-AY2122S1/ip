package duke;

public class DeleteCommand implements Executable{
    private int index;

    public DeleteCommand(int index) {
        this.index = index; 
    }

    public void execute(TaskList tasklist) {
        tasklist.deteteTask(index);
    }
}