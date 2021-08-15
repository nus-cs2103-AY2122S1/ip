//import java.util.List;
//import java.util.ArrayList;

public class TasksHandler {
    //private final List<Task> allTasks;

    public TasksHandler() {
        //this.allTasks = new ArrayList<Task>();
    }

    // will add checker to see if it is echo task or other tasks in future
    protected boolean addAndDisplayTasks(String[] instructions) {
        Echo echoTask = new Echo(instructions[0]);
        System.out.println(echoTask);
        return echoTask.isLastTask();
    }
    
}
