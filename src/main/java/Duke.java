import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");

        Scanner sc = new Scanner(System.in);
        String input = "";
        ArrayList<Task> ls = new ArrayList<>();

        while (true) {
            input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                if (ls.isEmpty()) {
                    System.out.println("There are currently no tasks in your list.");
                } else {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < ls.size(); i++) {
                        System.out.println((i+1) + "." + ls.get(i));
                    }
                }
            } else if (input.startsWith("done")) {
                int arrIndex = Integer.valueOf(input.substring(5)) - 1;
                Task task = ls.get(arrIndex);
                task.markAsDone();
                System.out.println(task.markedAsDoneToString());
            } else {
                Task task = new Task(input);
                ls.add(task);
                System.out.println("added: " + input);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

}
