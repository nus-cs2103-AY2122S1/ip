import java.util.ArrayList;

public class Task {
    private String name;
    private int order;
    private boolean done;
    private static ArrayList<Task> list_of_tasks = new ArrayList<>();
    private static int number_of_tasks = 0;

    public Task(String name) {
        number_of_tasks++;
        this.name = name;
        this.order = number_of_tasks;
        this.done = false;
        list_of_tasks.add(this);
    }

    public void log_add_task() {
        System.out.println("____________________________________________________________\n"
                + "added: " + name + "\n"
                + "____________________________________________________________");
    }

    private void finish_task() {
        this.done = true;
    }

    public static void print_list_of_tasks() {
        System.out.println("____________________________________________________________\n"
                + "Here are the tasks in your list: \n");
        for (Task t: list_of_tasks) {
            System.out.println(t.order + ". " + t.name + "\n");
        }
        System.out.println("____________________________________________________________\n");
    }

}
