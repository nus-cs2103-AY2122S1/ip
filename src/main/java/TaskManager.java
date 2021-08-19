import java.util.ArrayList;
import java.util.List;

/**
 * TaskManager keep track of the Task in the memory.
 */
public class TaskManager {
    private List<Task> taskList = new ArrayList<Task>();

    public TaskManager(){

    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * List all tasks inside the task manager.
     */
    public void listAll() {
        if (taskList.size() > 0) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                int index = i + 1;
                String marked = taskList.get(i).getStatusIcon();
                String taskInformation = taskList.get(i).getValue();
                System.out.println(index + ".[" + marked + "] " + taskInformation);
            }
            System.out.println();
        } else {
            System.out.println("Looks like there isn't any task!");
        }
    }

    public void markTaskDoneByIndex(int index) {
        Task taskToBeMarked = taskList.get(index - 1);
        taskToBeMarked.markDone();
        System.out.println("Nice! I've marked this task as done: ");
        String marked = taskToBeMarked.getStatusIcon();
        String taskInformation = taskToBeMarked.getValue();
        System.out.println("\t[" + marked + "] " + taskInformation);
    }

    /**
     * Get the total number of task.
     * @return the number of task in total.
     */
    public int getTotalNumberOfTask(){
        return taskList.size();
    }

}
