import java.util.Scanner;
import java.util.ArrayList;

public class Winston {
    private ArrayList<Task> list;

    public Winston() {
        list = new ArrayList<>();
    }

    private void addTask(String description) {
        list.add(Task.createTask(description));
    }

    public String getList() {
        Integer counter = 1;
        String result = "List\n";
        for (Task task : this.list) {
            result += "\t" + counter + ". " + task.getDescription() + "\n";
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
            } else if (!cmd.equals("")) {
                winston1.addTask(cmd);
                System.out.println("\tAdded " + cmd +  " to list");
            }
            cmd = scan.nextLine();
        }

        scan.close();
        System.out.println("See ya later!");
    }
}
