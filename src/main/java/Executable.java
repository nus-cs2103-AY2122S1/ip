import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * type to return after parsing
 * @author Tianqi-Zhu
 */
interface Executable {
    public void execute(ArrayList<Task> tasks, AtomicInteger taskAmount); 
}