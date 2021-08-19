package Duke.task;

import Duke.exception.NoSuchTaskException;

public class TaskList {
    private final Task[] list;
    private int taskCounter;

    public TaskList(int size){
        this.list = new Task[size];
        this.taskCounter = 0;
    }

    public void addTask(Task task){
        list[taskCounter] = task;
        taskCounter++;
        System.out.println("\nGot it. I've added this task:\n"
                + list[taskCounter - 1] + "\n" + this.listTaskAmount() + "\n");

    }

    public void listTasks(){
        System.out.println("\nHere are the tasks in your list:");
        for(int i = 0; i < list.length;i++){
            if(list[i] == null){
                break;
            }else {
                System.out.println((i + 1) + "." + list[i]);
            }
        }
        System.out.print("\n");
    }

    public String listTaskAmount(){
        return "Now you have " + taskCounter + " task(s) in the list.";
    }

    public void markDone(int taskPos) throws NoSuchTaskException {
        if(taskPos >= 0 && taskPos < taskCounter) {
            list[taskPos].markComplete();
            System.out.println("\nNice! I've marked this task as done:\n " + list[taskPos] + "\n");
        }else{
            throw new NoSuchTaskException();
        }
    }

}
