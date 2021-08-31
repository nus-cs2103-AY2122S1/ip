package duke;

import java.util.ArrayList;

/**
 * Represents the tasks in the list.
 */
public class TaskList {

    private Storage storage;

    /** The array list representing the tasks */
    private ArrayList<Task> arr;

    public TaskList(Storage storage) {
        this.storage=storage;
        this.arr=storage.load("data/duke.txt");
    }

    /**
     * Adds task to the task arraylist.
     * @param task Task to be added.
     */
    public void addTaskToList(Task task) {
        arr.add(task);
    }

    /**
     * Returns a specific indexed task from the task arraylist.
     * @param i Index of the task to be returned.
     * @return Task with index i.
     */
    public Task getTaskFromList(int i) {
        return arr.get(i);
    }

    /**
     * Returns number of tasks in the list.
     * @return number of tasks in the task arraylist.
     */
    public int numberOfTasks() {
        return arr.size();
    }

    /**
     * Deletes task from the list.
     * @param i Index of the task to be deleted.
     */
    public void deleteTask(int i) {
        arr.remove(i);
    }

    public String stringifyWholeList() {
        String res="";
        for (int j = 0; j < arr.size(); j++) {
            if (!arr.get(j).getPreExisting()) {
                res = res + arr.get(j).toString() + "\n";

            } else {
                res = res + arr.get(j).getDescription() + "\n";
            }
        }
        return res;
    }

    public ArrayList<Task> getTaskList() {
        return arr;
    }

    public String findTaskFromTaskList(TaskList ob, String inputFind) {
        String trimmedFind = inputFind.split("\\s", 2)[1];
        String res="";
        boolean flag = true;
        int i=1;
        for (int j = 0; j < ob.numberOfTasks(); j++) {
            if(!ob.getTaskFromList(j).getPreExisting()) {
                if(ob.getTaskFromList(j).toString().contains(trimmedFind)){
                    flag = false;
                    res = res + "    " + (i++) + ". " + ob.getTaskFromList(j).toString() + "\n";
                }
            } else {
                if(ob.getTaskFromList(j).getDescription().contains(trimmedFind)) {
                    flag = false;
                    res = res + "    " + (i++) + ". " + ob.getTaskFromList(j).getDescription() + "\n";
                }
            }
        }
        if(flag) {
            return "oops! sorry you do not have any matching tasks";
        }
        else{
            return res;
        }
    }

}
