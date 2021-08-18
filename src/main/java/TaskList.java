import java.util.ArrayList;

public class TaskList {
    static ArrayList<Task> storage = new ArrayList<>();

    public static void addToStorage(Task t){
        TaskList.storage.add(t);
        System.out.println("You have " + storage.size() + " tasks in the list");
        System.out.println(t);
    }

    public static void printTasks(){
        for (int i = 0; i < TaskList.storage.size(); i++) {
            System.out.println(TaskList.storage.get(i));
        }
    }

    public static void doneTask(int Id) throws DukeException{
        if(Id >= TaskList.storage.size()){
            throw new DukeException("no such task");
        }
        Task t = TaskList.storage.get(Id);
        t.markDone();
    }
}
