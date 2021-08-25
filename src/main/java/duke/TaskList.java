package duke;

import java.util.ArrayList;
import task.Task;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList(){
        tasks = new ArrayList<>();
    }

    /**
     * Adds Task to task list.
     *
     * @param t Task to add to task list.
     */
    public void add(Task t){
        this.tasks.add(t);
        System.out.println("You have " + tasks.size() + " tasks in the list");
        System.out.println(t);
    }

    /**
     * Adds Task to task list.
     *
     * @param s String stored in storage.
     */
    public void add(String s){
        this.tasks.add(Task.getTask(s));
    }

    /**
     * Prints all the Tasks in task list.
     */
    public void printTasks(){
        System.out.println(this.toString());
    }

    /**
     * Marks Task as done.
     *
     * @param Id Id of tasks done.
     * @throws DukeException if Id is invalid.
     */
    public void doneTask(int Id) throws DukeException{
        if(Id >= this.tasks.size()){
            throw new DukeException("no such task");
        }
        Task t = this.tasks.get(Id);
        t.markDone();
    }

    /**
     * Deletes Task from task list.
     *
     * @param Id Id of task to delete.
     * @throws DukeException if Id is invalid.
     */
    public void deleteTask(int Id) throws DukeException{
        if(Id >= this.tasks.size()){
            throw new DukeException("no such task");
        }
        this.tasks.remove(Id);
        System.out.println("removed Task " + Id);
    }

    /**
     * Finds Task that contains keyword.
     *
     * @param keyword Keyword used to find Task.
     */
    public void findTasks(String keyword){
        System.out.println("Matching task(s) are:");
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            String name = t.getName();
            if(name.contains(keyword)){
                System.out.println(i + ": " + t.toString());
            }
        }
    }

    /**
     * Returns string representing task list to be saved to text file.
     *
     * @return String representing task list
     */
    public String saveTasklist(){
        String txt = "";
        for (int i = 0; i < tasks.size(); i++) {
            txt = txt + tasks.get(i).saveTask() + "\n";
        }
        return txt;
    }

    @Override
    public String toString(){
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            out.append(i).append(": ").append(this.tasks.get(i).toString()).append("\n");
        }
        return out.toString();
    }
}
