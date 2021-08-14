import java.util.ArrayList;

/**
 * Class for each individual task and a list of the tasks
 */
public class Task {
    private String name;
    private int order;
    private boolean done;
    private static ArrayList<Task> list_of_tasks = new ArrayList<>();
    private static int number_of_tasks = 0;

    /**
     * public constructor to create a new task
     * @param name name of the task
     */
    public Task(String name) {
        number_of_tasks++;
        this.name = name;
        this.order = number_of_tasks;
        this.done = false;
        list_of_tasks.add(this);
    }

    /**
     * print log for adding a task
     */
    public void log_add_task() {
        System.out.println("____________________________________________________________\n"
                + "added: " + name + "\n"
                + "____________________________________________________________");
    }

    /**
     * change task's done state to be true
     */
    private void finish_task() {
        this.done = true;
        System.out.println("____________________________________________________________\n"
                + "Nice! I've marked this task as done: \n"
                + "[X] " + this.name + "\n"
                + "____________________________________________________________");
    }

    /**
     * find the task in the list and mark it as done
     * @param order order of the task
     */
    public static void find_finished_task(int order) {
        Task complete_task = list_of_tasks.get(order - 1);
        complete_task.finish_task();
    }

    /**
     * print list of task
     */
    public static void print_list_of_tasks() {
        System.out.println("____________________________________________________________\n"
                + "Here are the tasks in your list: \n");
        for (Task t: list_of_tasks) {
            System.out.println(t.order + "." + "[" + t.getStatus() + "] " + t.name + "\n");
        }
        System.out.println("____________________________________________________________\n");
    }

    public String getStatus() {
        return (done ? "X" : " ");
    }
}
