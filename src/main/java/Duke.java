import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {

        // ASCII divider to clean up the output
        final String divider = "---------------------------";

        // Initialise the taskList
        ArrayList<Task> taskList = new ArrayList<>();

        Scanner in = new Scanner(System.in);
        System.out.println(
                "Hello! I'm Duke" +
                        "\n" +
                        "What can I do for you?" +
                        "\n" +
                        divider);

        while (true) {
            String nextTask = in.nextLine();  // Read user input

            // Uses a series of checks to only check for "done (number)"
            if (nextTask.contains("done ")) {
                if (Character.isDigit(nextTask.charAt(5))) {
                    int taskIndex = Character.getNumericValue(nextTask.charAt(5)) - 1;
                    taskList.get(taskIndex).markAsDone();
                    System.out.println("Nice! I've marked this task as done: " +
                            "\n" +
                            taskList.get(taskIndex) +
                            "\n" +
                            divider);
                }
                // case where "done" is a task e.g. "done my homework"
                else {
                    taskList.add(new Task(nextTask));
                    System.out.println(divider + "\n" + "added: " + nextTask + "\n" + "\n" + divider);
                }
                continue;
            }

            // If we want to show the list, it has to not add it to taskList
            if (nextTask.equals("list")) {
                System.out.println(divider);
                System.out.println("Here are the items in your To Do List: ");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println(i + 1 + ". " + taskList.get(i));
                }
                System.out.println("\n" + divider);
                continue;
            }


            if (nextTask.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            taskList.add(new Task(nextTask));
            System.out.println(divider + "\n" + "added: " + nextTask + "\n" + "\n" + divider);
        }
    }
}
