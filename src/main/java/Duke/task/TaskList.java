package Duke.task;

import Duke.exception.NoSuchTaskException;
import java.util.ArrayList;


public class TaskList {
    private final ArrayList<Task> list;

    public TaskList(){
        this.list = new ArrayList<>();
    }

    public void addTask(Task task){
        list.add(task);
        System.out.println("\nGot it. I've added this task:\n"
                + list.get(list.size() - 1) + this.listTaskAmount());
    }

    public void listTasks(){
        System.out.println("\nHere are the tasks in your list:");
        for(int i = 0; i < list.size();i++){

                System.out.println((i + 1) + "." + list.get(i));

        }
        System.out.print("\n");
    }

    public String listTaskAmount(){
        return "\nNow you have " + list.size() + " task(s) in the list.\n";
    }

    public void markDone(int taskPos) throws NoSuchTaskException {
        if(taskPos >= 0 && taskPos < list.size()) {
            list.get(taskPos).markComplete();
            System.out.println("\nNice! I've marked this task as done:\n " + list.get(taskPos) + "\n");
        }else{
            throw new NoSuchTaskException();
        }
    }

    public void deleteTask(int taskPos) throws NoSuchTaskException {

        if(taskPos >= 0 && taskPos < list.size()) {
            Task temp = list.get(taskPos);
            list.remove(taskPos);
            System.out.println("\nOk, I have removed the following task:\n " + temp + this.listTaskAmount());
        }else{
            throw new NoSuchTaskException();
        }
    }

}
