public class Nature {

    private final Task[] taskList = new Task[100];
    private int taskCount = 0;

    public String greeting() {
        return "Hello! I'm Nature.\n"
                + "What can I do for you?";
    }

    public String farewell() {
        return "Bye darling. \n"
                + "Hope to see you again soon!";
    }

    public void addTask(String task) {
        Task t = new Task(task, taskCount + 1);
        if (taskCount <= 100) {
            taskList[taskCount] = t;
            taskCount++;
        }
        else System.out.println("Can't add more tasks!");
    }

    public void printTaskList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println(taskList[i]);
        }
    }

    public void markTaskDone(int index) {
        if (index > taskCount) {
            System.out.println("Invalid index");
        } else {
            Task t = taskList[index - 1];
            t.setDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(t.showAsDone());
        }
    }


}
