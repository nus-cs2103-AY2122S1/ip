import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class ListTask implements Executable {
    public void execute(ArrayList<Task> tasks, AtomicInteger taskAmount) {
        if (tasks.size() == 0) {
            Processor.printString("No current task available.");
        } else {
            String out = "Here are the tasks in your list:\n";
            for (int i = 0; i < tasks.size(); i++) {
                int index = i + 1;
                out = out + Processor.spaceString + index + "." + tasks.get(i) + "\n";
            }
            Processor.printList(out);
        }
    }
}