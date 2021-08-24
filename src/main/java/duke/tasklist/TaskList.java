package duke.tasklist;

import duke.tasks.*;
import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<Task>();
    }

    // If theres existing tasks
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    // This method adds elements to the end of the TaskList
    public void add(Task task) {
        taskList.add(task);
    }


    // This method returns the size of taskList
    public int size() {
        return taskList.size();
    }


    // This method returns the task in taskList at the index specified
    public Task get(int index) {
        return taskList.get(index);
    }

    public String getLastStatusString() {
        return taskList.get(taskList.size() - 1).getStatusString();
    }


    // This method returns the entire taskList
    public ArrayList<Task> getAll() {
        return taskList;
    }

    public void remove(int index) {
        taskList.remove(index);
    }

    public ArrayList<String> search(String word) {
        ArrayList<String> wordList = new ArrayList<String>();
        for (Task t : taskList) {
            if (t.toString().contains(word)) {
                wordList.add(t.toString());
            }
        }
        return wordList;
    }
}
