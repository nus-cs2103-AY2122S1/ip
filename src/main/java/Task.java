public class Task {
    private String description;
    private boolean isDone;
    private static Task[] tasks = new Task[100];
    private static int counter = 0;

    private Task(String description) {
        this.description = description;
        this.isDone = false;
        Task.tasks[counter] = this;
        Task.counter++;
    }

    private String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public void taskDone() {
        this.isDone = true;
    }

    public static void markDone(int index) {
        tasks[index].taskDone();
    }

    public static void add(String input) {
        new Task(input);
    }

    public static Task retrieveTask(int index) {
        return tasks[index];
    }

    public static void displayList() {
        System.out.println("\nHere are the tasks in your list:\n--------------------");
        for (int i = 0; i < tasks.length; i++) {
            Task task = tasks[i];
            if (task == null) {
                if (i == 0) System.out.println("List is empty!");
                break;
            }
            int index = i + 1;
            System.out.println(index + ". " + tasks[i]);
        }
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
