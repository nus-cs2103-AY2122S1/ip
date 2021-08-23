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
        Processor.printString("Noted. I've removed this task:\n  " + Processor.SPACE_STRING + removedTask + "\n" + Processor.SPACE_STRING +
             "Now you have " + taskAmount + " tasks in the list.");
        Processor.save(tasks);
    }
}