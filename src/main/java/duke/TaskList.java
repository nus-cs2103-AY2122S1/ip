package duke;

import duke.tasks.Task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    private int counter = 0;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public void add(Task task) {
        if (!taskList.contains(task)) {
            this.taskList.add(task);
            this.counter++;
        }
    }

    public Task delete(int index) {
        Task deletedTask = this.taskList.get(index - 1);
        this.taskList.remove(index - 1);
        this.counter--;
        return deletedTask;
    }

    public Task get(int index) {
        return this.taskList.get(index);
    }

    public int size() {
        return this.counter;
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public Task setDone(int index) {
        this.taskList.get(index - 1).setDone();
        return this.taskList.get(index - 1);
    }

    /**
     * Method to find all tasks whose description match the input string
     *
     * @param searchedString The input string
     * @return Tasklist of all tasks whose description match input string
     */
    public TaskList findTasks(String searchedString) {
        String[] searchedStrArr = searchedString.split(" ");
        TaskList tasksFound = new TaskList();

        for (int i = 0; i < searchedStrArr.length; i++) {

            String string = searchedStrArr[i];

            for (int j = 0; j  < taskList.size(); j++) {
                Task task = taskList.get(j);

                if (task.getDescription().contains(string)) {
                    tasksFound.add(task);
                }
            }
        }
        return tasksFound;
    }

    @Override
    public String toString() {
        String tasks = "";
        for (int i = 0; i < counter; i++) {
            tasks += (i + 1) + ". " + this.taskList.get(i) + "\n";
        }
        return tasks;
    }
}
