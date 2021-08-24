import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private int count;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
        this.count = 0;
    }
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.count = tasks.size();
    }

    public void addEventTask(EventTask task) {
        tasks.add(task);
        count++;
    }

    public void addDeadlineTask(DeadlineTask task) {
        tasks.add(task);
        count++;
    }

    public void addToDoTask(ToDoTask task) {
        tasks.add(task);
        count++;
    }

    public void showList() {
        int counter = 1;
        System.out.println(" Here are the tasks in your list:");
        for (Task item: tasks) {
            if(item != null) {
                System.out.println(counter + ". " + item.toString());
                counter++;
            }

        }
    }

    public void doTask(int task) {
        if (!tasks.get(task-1).isDone) {
            System.out.println("Nice! I've marked this task as done:");
            tasks.get(task - 1).markAsDone();
            System.out.println(tasks.get(task - 1));
        } else {
            System.out.println("This task is already marked as done");
        }
    }

    public void deleteTask(int task) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(tasks.get(task - 1));
        tasks.remove(task-1);
        count--;
    }
}
