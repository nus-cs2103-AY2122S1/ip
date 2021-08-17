import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class DeleteTask implements Executable{
    private int index;

    public DeleteTask(int index) {
        this.index = index; 
    }

    public void execute(ArrayList<Task> tasks, AtomicInteger taskAmount) {
        Task removedTask = tasks.remove(index - 1); 
        taskAmount.set(taskAmount.get() - 1);
        Processor.printString("Noted. I've removed this task:\n  " + Processor.spaceString + removedTask + "\n" + Processor.spaceString +
             "Now you have " + taskAmount + " tasks in the list.");
    }
}