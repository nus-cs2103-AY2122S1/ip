package duke;

import java.util.ArrayList;

/**
 * TaskList class that contains a list of Task and
 * methods to modify them
 */
public class TaskList {
    protected ArrayList<Task> list;

    /**
     * Creates a TaskList object
     */
    TaskList(){
        this.list = new ArrayList<>();
    }

    /**
     * Creates a TaskList object
     * @param list takes in a list of Task
     */
    TaskList(ArrayList<Task> list){
        this.list = list;
    }

    /**
     * Adds a task to the list
     * @param t takes in a Task to be added
     */
    public void add(Task t){
        list.add(t);
    }

    /**
     * Gets the task in the specific index
     * @param getNum takes in a int representing the index
     * @return a Task at the specific index
     */
    public Task get(int getNum){
        return list.get(getNum);
    }

    /**
     * Gets the size of the list
     * @return a int representing the list size
     */
    public int size(){
        return list.size();
    }

    /**
     * Removes a task at the specific index from the list
     * @param delNum takes in a int representing the index
     */
    public void remove(int delNum){
        list.remove(delNum);
    }

}
