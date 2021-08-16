import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {

        String divider = "---------------------------";

        ArrayList<String> taskList = new ArrayList<>();

        Scanner in = new Scanner(System.in);
        System.out.println(
                "Hello! I'm Duke" +
                        "\n" +
                        "What can I do for you?" +
                        "\n" +
                        divider);

        while (true) {
            String nextTask = in.nextLine();  // Read user input


            if (nextTask.equals("list")) {
                System.out.println(divider);
                System.out.println("Here are the items in your To Do List: ");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println(i+1 + ". " + taskList.get(i));
                }
                System.out.println("\n" + divider);
                continue;
            }

            taskList.add(nextTask);

            if (nextTask.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            System.out.println(divider + "\n" + "added: " + nextTask + "\n" + "\n" + divider);
        }
    }
}
