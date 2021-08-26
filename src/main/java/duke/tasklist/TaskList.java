package duke.tasklist;

import duke.exceptions.InvalidNumberInputException;
import duke.tasks.Task;
import java.util.ArrayList;

/**
 * The TaskList is an abstraction for a list of tasks
 */
public class TaskList {

    private final ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public Task get(int i) throws InvalidNumberInputException {
        return taskList.get(i);
    }

    public int size() {
        return taskList.size();
    }

    /**
     * Prints the list of tasks
     */
    public void print() {
        boolean allTasksDone = true;
        for(int i = 0; i < this.size(); i++) {
            int ind = i + 1;
            System.out.println(ind + ". " + this.get(i).toString());
            if (!this.get(i).getStatus()) {
                allTasksDone = false;
            }
        }
        if (allTasksDone) {
            System.out.println("All tasks are complete!!");
        }
    }

    public Task remove(int i) {
        return taskList.remove(i);
    }

    public void printRemainingTasks() {
        if (taskList.size() == 1) {
            System.out.println("Now you have 1 task in the list.");
        } else {
            System.out.printf("Now you have %s tasks in the list.\n", taskList.size());
        }
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    /**
     * Checks if a list of tasks contains the given task
     *
     * @param task task to be checked
     * @return boolean value of whether the task is contained in the list
     */
    public boolean containsTask(Task task) {
        boolean ans = false;
        for (Task value : taskList) {
            if (value.getDetails().trim().equals(task.getDetails().trim())) {
                ans = true;
                break;
            }
        }
        return ans;
    }
}
