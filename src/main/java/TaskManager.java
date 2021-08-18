import java.util.ArrayList;
import java.util.List;

/**
 * TaskManager keep track of the Task in the memory.
 */
public class TaskManager {
    List<Task> taskList = new ArrayList<Task>();

    public TaskManager(){

    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void listAll() {
        if (taskList.size() > 0) {
            for (int i = 0; i < taskList.size(); i++) {
                int index = i + 1;
                String taskInformation = taskList.get(i).getValue();
                System.out.println(index + ". " + taskInformation);
            }
            System.out.println();
        } else {
            System.out.println("Looks like there isn't any task!");
        }

    }

}
