package duke.tasklist;

import duke.exceptions.InvalidNumberInputException;
import duke.task.Task;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public Task get(int i) throws InvalidNumberInputException {
        return taskList.get(i);
    }

    public int size() {
        return taskList.size();
    }

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
            System.out.println("Now you have 1 duke.task in the list.");
        } else {
            System.out.printf("Now you have %s tasks in the list.%n", taskList.size());
        }
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    public boolean containsTask(Task task) {
        boolean ans = false;
        for (Task value : taskList) {
            if (value.getDetails().trim().equals(task.getDetails().trim())){
                ans = true;
                break;
            }
        }
        return ans;
    }
}
