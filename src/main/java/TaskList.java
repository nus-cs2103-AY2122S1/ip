public class TaskList {
    private Task[] list;
    private int taskCounter;

    public TaskList(int size){
        this.list = new Task[size];
        this.taskCounter = 0;
    }

    public void addTask(Task task){
        list[taskCounter] = task;
        taskCounter++;
        System.out.println("Got it. I've added this task: \n"
                + list[taskCounter - 1] + "\nNow you have "
                + taskCounter + " tasks in the list.");

    }

    public void list(){
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < list.length;i++){
            if(list[i] == null){
                break;
            }else {
                System.out.println((i + 1) + "." + list[i]);
            }
        }
    }

    public void markDone(int taskPos){
        list[taskPos].markComplete();
        System.out.println("Nice! I've marked this task as done:\n " + list[taskPos]);
    }

}
