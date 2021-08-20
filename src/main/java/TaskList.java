import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> storage;

    public TaskList(){
        storage = new ArrayList<>();
    }

    public void addToStorage(Task t){
        this.storage.add(t);
        System.out.println("You have " + storage.size() + " tasks in the list");
        System.out.println(t);
    }
    public void addToStorage(String s){
        this.storage.add(Task.getTask(s));
    }

    public void printTasks(){
        for (int i = 0; i < this.storage.size(); i++) {
            System.out.println(i + ": " + this.storage.get(i));
        }
    }

    public void doneTask(int Id) throws DukeException{
        if(Id >= this.storage.size()){
            throw new DukeException("no such task");
        }
        Task t = this.storage.get(Id);
        t.markDone();
    }

    public void deleteTask(int Id) throws DukeException{
        if(Id >= this.storage.size()){
            throw new DukeException("no such task");
        }
        this.storage.remove(Id);
        System.out.println("removed Task " + Id);
    }

    public String saveTasklist(){
        String txt = "";
        for (int i = 0; i < storage.size(); i++) {
            txt = txt + storage.get(i).saveTask() + "\n";
        }
        return txt;

    }
}
