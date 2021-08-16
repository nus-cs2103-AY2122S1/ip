import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        markAsDone();
    }

    /**
     * Method for duke to mark tasks as done.
     * Inputs are taken by a scanner from the user's keyboard.
     */
    public static void markAsDone() {
        ArrayList<Task> taskList = new ArrayList<>();
        System.out.println("-----------------------------------------");
        System.out.println(" Hello! I am Duke");
        System.out.println(" What can I do for you?");
        System.out.println("-----------------------------------------");
        System.out.println();

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {

            String command = sc.nextLine();
            Task task = new Task(command);
            String[] inputValue = command.split(" ");

            if (command.equals("bye")) {
                System.out.println("    -----------------------------------------");
                System.out.println("     Bye. Hope to see you again soon!");
                System.out.println("    -----------------------------------------");
                break;
            } else if (command.equals("list")) {
                System.out.println("    -----------------------------------------");
                System.out.println(("    Here are the tasks in your list:"));
                for (int i = 0; i < taskList.size(); i++) {
                    int currNum = i + 1;
                    Task currTask = taskList.get(i);
                    System.out.println("     " + currNum + ". " + "[" + currTask.getStatusIcon() + "] "
                            + currTask.getDescription());
                }
                System.out.println("    -----------------------------------------");
                System.out.println();
            } else if (inputValue.length == 2 && inputValue[0].equals("done")) {
                int idx = Integer.parseInt(inputValue[1]);
                Task taskToComplete = taskList.get(idx - 1);
                taskToComplete.setIsDone(true);
                System.out.println("    -----------------------------------------");
                System.out.println("     Nice! I've marked this task as done:");
                System.out.println("       [" + taskToComplete.getStatusIcon() + "] " + taskToComplete.getDescription());
                System.out.println("    -----------------------------------------");
                System.out.println();
            } else {
                taskList.add(task);
                System.out.println("    -----------------------------------------");
                System.out.println("     added: [" + task.getStatusIcon() + "] " + task.getDescription());
                System.out.println("    -----------------------------------------");
                System.out.println();
            }
        }
        sc.close();
    }
}
