package bob;

import bob.exception.NoSearchResultException;
import bob.task.Task;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.taskList = tasks;
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public String getList() {
        String result = "";
        for (int index = 0; index < this.taskList.size(); index++) {
            result = result + (index + 1) + "." + this.taskList.get(index).printTask() + "\n";
        }
        return result;
    }

    public String markIndexCompleted(int index) {
        Task selectedTask = this.taskList.get(index);
        selectedTask.markCompleted();
        return selectedTask.printTask();
    }

    public String deleteIndex(int index) {
        Task selectedTask = this.taskList.get(index);
        this.taskList.remove(index);
        return selectedTask.printTask();
    }

    public String noOfTasks() {
        return Integer.toString(this.taskList.size());
    }

    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    public String searchList(String keyword) throws NoSearchResultException {
        String result = "";
        int count = 1;
        for (int index = 0; index < this.taskList.size(); index++) {
            String currTask = this.taskList.get(index).printTask();
            String currTaskDescription = currTask.split("\\Q[\\E.\\Q]\\E ", 2)[1];
            if (currTaskDescription.contains(keyword)) {
                result = result + count + "." + currTask + "\n";
                count++;
            }
        }
        if (count == 1) {
            throw new NoSearchResultException();
        } else {
            return result;
        }
    }
}
