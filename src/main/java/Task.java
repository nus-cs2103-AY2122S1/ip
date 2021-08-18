import java.util.ArrayList;

public class Task {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private boolean completed;
    private String task;

    public Task(String task) {
        this.completed = false;
        this.task = task;
    }

    public String getTask() {
        return this.task;
    }

    public static void addReply(Task assignment) {
        tasks.add(assignment);
        System.out.println("Got it. I've added this task");
        System.out.println(assignment);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }
    public static void listReply() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(tasks.get(i));
        }
    }
    public static String getListItem(int i) {
        String tick = tasks.get(i).isCompleted() ? "[X] " : "[ ] ";
        int num = i + 1;
        String str = tick + num + ". " + tasks.get(i);
        return str;
    }

    public static String getLatestItem() {
        return getListItem(tasks.size()-1);
    }

    public boolean isCompleted() {
        return this.completed;
    }

    public void setComplete() {
        this.completed = true;
    }

    public int getIndex() {
        return tasks.indexOf(this);
    }

    public static void done(String index_1) {
        int i = Integer.parseInt(index_1) - 1;
        Task assignment = tasks.get(i);
        assignment.setComplete();
        System.out.println("Done! I've marked this task as done!");
        System.out.println(assignment);

    }

    @Override
    public String toString() {
        String tick = this.isCompleted() ? "[X] " : "[ ] ";
        int num = this.getIndex() + 1;
        String str = tick + num + ". " + this.task;
        return str;
    }

}

