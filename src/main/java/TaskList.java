import java.util.ArrayList;
import java.util.List;

import tasks.Task;

public class TaskList {

    private List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = new ArrayList<>(taskList);
    }

    public Task get(int index) {
        return taskList.get(index);
    }

    public void addTask(Task t) {
        taskList.add(t);
    }

    public Task deleteTask(int index) {
        return taskList.remove(index);
    }

    public int size() {
        return taskList.size();
    }

    /**
     * Returns string representation of list items to be printed on screen.
     *
     * @param str string to be displayed before list.
     * @return string representation of list items to be printed on screen.
     */
    public String listToString(String str) {
        String result = str;
        if (taskList.size() == 0) {
            result = result.concat("\n\n---No items added yet ---\n");
        } else {
            int ctr = 1;
            for (Task t: taskList) {
                result = result.concat("\n" + ctr + ". " + taskList.get(ctr - 1));
                ctr++;
            }
            System.out.println(result);
        }

        return result + "\n";
    }

    /**
     * Prints list of tasks.
     */
    public void printList() {
        int ctr = 1;
        for (Task task: taskList) {
            System.out.println("\t" + ctr + "." + taskList.get(ctr - 1));
            ctr++;
        }
    }

    /**
     * Finds tasks which contain the given keyword.
     *
     * @param keyword specifies keyword to be searched for.
     * @return list of tasks that contain the given keyword.
     */
    public TaskList findMatchingTasks(String keyword) {
        ArrayList<Task> matchList = new ArrayList<>();
        for (Task task : taskList) {
            if (task.toString().contains(keyword)) {
                matchList.add(task);
            }
        }
        return new TaskList(matchList);
    }

    public List<Task> getTaskList() {
        return taskList;
    }
}
