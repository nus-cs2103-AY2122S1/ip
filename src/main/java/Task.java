public class Task {
    private String[] tasks;
    private int[] taskCompletion; // 1 for done and 0 for incomplete
    private int numOfTasks;

    //This is the constructor
    public Task() {
        this.tasks = new String[100];
        this.taskCompletion = new int[100];
        this.numOfTasks = 0;
    }

    //This function will add a new task to the list of tasks
    public void addNewTask(String str) {
        tasks[numOfTasks] = str;
        taskCompletion[numOfTasks] = 0;
        numOfTasks++;
    }

    //This function will change the status of the task from incomplete to done
    public void markStatusAsDone(int i) {
        taskCompletion[i-1] = 1;
    }

    //This function will give you the task at that particular position
    public String getTasks(int i) {
        return tasks[i - 1];
    }

    //This function will give the number of tasks
    public int getNumOfTasks() {
        return numOfTasks;
    }

    //This function will print the list of tasks with their status as done or incomplete
    public void printTasks() {
        for(int i = 0; i < numOfTasks; i++) {
            String symbol = "";
            if(taskCompletion[i] == 1) {
                symbol = "✗";
            } else {
                symbol = " ";
            }
            System.out.println("            " + (i+1) + ".[" + symbol + "] " + tasks[i]);
        }
    }
}
