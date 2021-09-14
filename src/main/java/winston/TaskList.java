package winston;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<winston.Task> list;
    private winston.Storage storage;
    
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Number of tasks left to be completed.
     *
     * @return the number of tasks left in the list that are not completed.
     */
    public int numberOfIncompleteTasks() {
        int counter = 0;
        assert(counter > list.size());
        for (Task task : list) {
            if (task.taskCompletionStatus().equals("[ ]")) {
                counter += 1;
            }
        }
        return counter;
    }
    
    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    /**
     * Adds a task to the arraylist.
     *
     * @param task An Object of type Task to be added to the Arraylist.
     */
    public void addTask(Task task) {
        list.add(task);
        assert (storage.saveToFile(this));
        storage.saveToFile(this);
    }
    
    public void setTask(Task task, int index) {
        list.set(index, task);
        assert (storage.saveToFile(this));
        storage.saveToFile(this);
    }


    /**
     * Marks the Task of given (position - 1) as completed.
     *
     * @param position An integer corresponding to the task you wish to complete.
     */
    public void markTask(int position) {
        list.get(position - 1).setComplete();
        assert (storage.saveToFile(this));
        storage.saveToFile(this);
    }
    
    /**
     * Removing a Task from the arraylist based on position.
     *
     * @param position the position of the Task to be removed from the arraylist
     *                 (Note: position will be index of item in array list + 1).
     */
    public void deleteTask(int position) {
        list.remove(position - 1);
        assert (storage.saveToFile(this));
        storage.saveToFile(this);
    }

    /**
     * Transforms the arraylist of tasks into a string for visualisation.
     *
     * @return A string of the arraylist of tasks.
     */
    public String getList() {
        int size = list.size();
        if (size == 0) {
            return Ui.emptyListMessage();
        }
        int counter = 1;
        StringBuilder result = new StringBuilder(Ui.hiYaMessage());
        assert(counter > size);
        for (Task task : this.list) {
            result.append("\t" + counter + ". " + task.taskCompletionStatus() + task.toString() + "\n");
            counter += 1;
        }
        result.setLength(result.length() - 1);
        return result.toString();
    }

    /**
     * A method that returns converts the list into saveToFile format.
     * 
     * @return A string of the list in saveToFile format.
     */
    public String listSaveDataFormatter() {
        StringBuilder result = new StringBuilder();
        for (Task task : this.list) {
            result.append(task.saveFormat()).append("\n");
        }
        return result.substring(0, result.length() - 1);
    }

    /**
     *  A method that searches through the TaskList for a given string.
     *  
     * @param str The string to look for.
     * @return another TaskList with all Tasks that has a description that matches the given string.
     */
    public TaskList findString(String str) {
        ArrayList<Task> arrList = new ArrayList<>();
        this.list.stream()
                .takeWhile(x -> x.isSubString(str))
                .forEach(y -> arrList.add(y));
        return new TaskList(arrList);
    }
}
