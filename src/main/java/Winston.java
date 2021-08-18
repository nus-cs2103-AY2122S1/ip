import java.util.Scanner;
import java.util.ArrayList;

/**
 * A class that creates your personal assistant Winston
 */
public class Winston {
    private ArrayList<Task> list;

    /**
     * Constructor for class Winston
     */
    public Winston() {
        list = new ArrayList<>();
    }

    /**
     * Creates a new task and adds the task into the arraylist in the overarching class
     *
     * @param description a string of the description of the task
     */
    private void addTask(String description) {
        list.add(Task.createTask(description));
    }

    /**
     * Marks the Task of given (position - 1) as completed
     *
     * @param position An integer corresponding to the task you wish to complete
     */
    public void markTask(Integer position) {
        list.get(position - 1).setComplete();
    }

    /**
     * Transforms the arraylist of tasks into a string for visualisation
     *
     * @return A string of the arraylist of tasks
     */
    public String getList() {
        Integer counter = 1;
        String result = "List of things to do:\n";
        for (Task task : this.list) {
            result += "\t" + counter + ". " + "[";
            if (task.getProgress()) {
                result += "X";
            } else {
                result += " ";
            }
            result += "] " + task.getDescription() + "\n";
            counter += 1;
        }
        result = result + "End";
        return result;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Hi there! Winston reporting.\nWhat can I do for you?");
        String cmd = "";
        Winston winston1 = new Winston();

        while (!cmd.equals("bye")) {
            if (cmd.equals("list")) {
                System.out.println(winston1.getList());
            } else if (cmd.equals("done")) {
                System.out.println(winston1.getList());
                System.out.println("Which task number have you completed?");
                winston1.markTask(scan.nextInt());
                System.out.println("Don't worry, I've got you. Task Marked!");
                System.out.println(winston1.getList());
            } else if (!cmd.equals("")) {
                System.out.println(cmd);
                winston1.addTask(cmd);
                System.out.println("\tAdded " + cmd +  " to list");
            }
            cmd = scan.nextLine();
        }

        scan.close();
        System.out.println("See ya later!");
    }
}
