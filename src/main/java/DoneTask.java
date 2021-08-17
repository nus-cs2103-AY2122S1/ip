import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class DoneTask implements Executable{
    private int index;

    public DoneTask(int index) {
        this.index = index; 
    }

    public void execute(ArrayList<Task> tasks, AtomicInteger taskAmount) {
        tasks.get(index - 1).done();
        Processor.printString("Nice, I've marked this as done!\n" + Processor.spaceString + "  " + tasks.get(index - 1));
    }
}