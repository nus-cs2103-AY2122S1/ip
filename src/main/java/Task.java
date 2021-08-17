import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Task implements Executable {
    protected String name; 
    protected boolean isDone = false; 

    public Task(String name) {
        this.name = name; 
    }
    
    public void done() {
        isDone = true; 
    }

    public String name() {
        return name;
    }

    public void execute(ArrayList<Task> tasks, AtomicInteger taskAmount) {
        tasks.add(this);
        taskAmount.set(taskAmount.get() + 1);
        Processor.printString("Got it. I've added this task:\n  " + Processor.spaceString + this + "\n" + Processor.spaceString + "Now you have " + taskAmount + " tasks in the list.");
    }

    public String toString() {
        if (isDone) {
            return "[X] " + name; 
        } else {
            return "[ ] " + name; 
        }
    }
}