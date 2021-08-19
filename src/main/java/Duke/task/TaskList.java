package Duke.task;

import Duke.exception.NoSuchTaskException;
import java.util.ArrayList;


public class TaskList {
    private final ArrayList<Task> list;
    private int taskCounter;

    public TaskList(){
        this.list = new ArrayList<>();
        this.taskCounter = 0;
    }

    public void addTask(Task task){
        list.add(task);
        taskCounter++;
        System.out.println("\nGot it. I've added this task:\n"
                + list.get(taskCounter - 1) + "\n" + this.listTaskAmount() + "\n");
    }

    public void listTasks(){
        System.out.println("\nHere are the tasks in your list:");
        for(int i = 0; i < list.size();i++){

                System.out.println((i + 1) + "." + list.get(i));

        }
        System.out.print("\n");
    }

    public String listTaskAmount(){
        return "Now you have " + taskCounter + " task(s) in the list.";
    }

    public void markDone(int taskPos) throws NoSuchTaskException {
        if(taskPos >= 0 && taskPos < taskCounter) {
            list.get(taskPos).markComplete();
            System.out.println("\nNice! I've marked this task as done:\n " + list.get(taskPos) + "\n");
        }else{
            throw new NoSuchTaskException();
        }
    }

}
