import java.util.ArrayList;

public class Task {
    protected String name;
    protected boolean isDone;
    private static ArrayList<Task> listOfTasks = new ArrayList<Task>();

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.name;
    }

    public static void addTask(Task t) {
        listOfTasks.add(t);
        System.out.println("Okay! Task added:\n  " + t.toString());
        System.out.println("You now have " + listOfTasks.size() + " task(s) in the list.");
    }

    public static void listAllTasks() {
        System.out.println("Here are the tasks in your list:");
        int size = listOfTasks.size();
        for (int i = 0; i < size; i++) {
            Task t = listOfTasks.get(i);
            System.out.println((i + 1) + "." + t.toString());
        }
    }

    public static void deleteTask(int index) {
        int numOfTasks = listOfTasks.size();
        if (index >= numOfTasks) {
            System.out.println("No task of this number. Add new task or input a different number.");
        } else if (index < 0) {
            System.out.println("Input a task number from 1 - " + numOfTasks);
        } else {
            Task t = listOfTasks.get(index);
            listOfTasks.remove(index);
            System.out.println("Ok! I've deleted this task:\n  " + t.toString());
            System.out.println("You now have " + (numOfTasks - 1) + " task(s) in the list.");
        }
    }

    public static void taskDone(int index) {
        int numOfTasks = listOfTasks.size();
        if (index >= numOfTasks) {
            System.out.println("No task of this number. Add new task or input a different number.");
        } else if (index < 0) {
            System.out.println("Input a task number from 1 - " + numOfTasks);
        } else {
            Task t = listOfTasks.get(index);
            t.markAsDone();
            System.out.println("Nice! I've marked this task as done:\n  " + t.toString());
        }
    }
}
