package duke.tasklist;

import duke.task.Task;

import java.util.ArrayList;

/**
 * <code>TaskList</code> class represents a list of Tasks with added functionality
 * Initialise a TaskList with an existing ArrayList of Tasks
 * or without one to start from scratch with an empty TaskList
 *
 * Implements 3 getter methods getList(), getSize() and getTask(index)
 * and 4 other methods add(task), delete(index), markDone(index) and returnAllTasks()
 */
public class TaskList{
    //contains the list of tasks and operations to the list.
    protected ArrayList<Task> tasks;

    //if list cannot be loaded, create an empty list to start
    public TaskList(){
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks){ this.tasks = tasks; }

    public ArrayList<Task> getList(){
        return this.tasks;
    }

    public int getSize(){
        return this.tasks.size();
    }

    public Task getTask(int index){
        return this.tasks.get(index);
    }

    //add a task at the end of the list
    public void add(Task task){
        this.tasks.add(task);
    }

    //delete a task at a specific index
    public void delete(int index){
        this.tasks.remove(index);
    }

    //mark task at specific index as done
    public void markDone(int index){
        this.tasks.get(index).setDone();
    }

    /**
     * <code>returnAllTasks()</code> returns a response string of all tasks in the current TaskList.
     *
     * @return output string of all tasks in the current TaskList concatenated together.
     */
    public String returnAllTasks() {
        String output = "";
        int taskNumber = 1;
        for (Task t : tasks) {
            output += taskNumber + ". ";
            output += t.toString();
            output += "\n";
            taskNumber++;
        }
        return output;
    }

}
