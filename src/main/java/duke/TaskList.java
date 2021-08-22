package duke;

import java.util.ArrayList;
import task.Task;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList(){
        tasks = new ArrayList<>();
    }

    public void add(Task t){
        this.tasks.add(t);
        System.out.println("You have " + tasks.size() + " tasks in the list");
        System.out.println(t);
    }

    public void add(String s){
        this.tasks.add(Task.getTask(s));
    }

    public void printTasks(){
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println(i + ": " + this.tasks.get(i));
        }
    }

    public void doneTask(int Id) throws DukeException{
        if(Id >= this.tasks.size()){
            throw new DukeException("no such task");
        }
        Task t = this.tasks.get(Id);
        t.markDone();
    }

    public void deleteTask(int Id) throws DukeException{
        if(Id >= this.tasks.size()){
            throw new DukeException("no such task");
        }
        this.tasks.remove(Id);
        System.out.println("removed Task " + Id);
    }

    public String saveTasklist(){
        String txt = "";
        for (int i = 0; i < tasks.size(); i++) {
            txt = txt + tasks.get(i).saveTask() + "\n";
        }
        return txt;

    }
}
