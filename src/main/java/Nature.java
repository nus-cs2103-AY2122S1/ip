public class Nature {

    private String message;
    private final String[] taskList = new String[100];
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
        if (taskCount <= 100) {
            taskList[taskCount] = task;
            taskCount++;
        }
        else System.out.println("Can't add more tasks!");
    }

    public void printTaskList() {
        for (int i = 0; i < taskCount; i++) {
            int index = i + 1;
            System.out.println(index + ". " + taskList[i]);
        }
    }

}
