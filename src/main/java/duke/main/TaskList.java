package duke.main;

import duke.task.Task;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    // For ToDo, event, deadline
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    // For done command
    public void markTaskAsDone(int index) {
        this.tasks.get(index).markAsDone();
    }

    // For delete command
    public void deleteTask(int index) {
        this.tasks.remove(index);
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    // For List command
    @Override
    public String toString() {
        String str = "";
        int size = tasks.size();
        if (size == 0) {
            str = "\t Nothing has been added to the list";
        }

        for (int i = 0; i < size; i++) {
            str += "\t " + (i + 1) + ". " + this.tasks.get(i) + "\n";
        }

        return str;
    }

    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Method which finds all the Task in the TaskList that match the keyword given.
     *
     * @param keyword The keyword entered by the user.
     * @return TaskList The resultant TaskList that contains all the Task that match the keyword.
     */
    public TaskList findMatchingTask(String keyword) {
        ArrayList<Task> resultList = new ArrayList<>();

        for (Task t : this.tasks) {
            if (t.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                resultList.add(t);
            }
        }

        return new TaskList(resultList);
    }
}
