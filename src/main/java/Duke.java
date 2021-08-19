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
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);
        String a = sc.nextLine();
        String[] b = a.split(" ", 2);

        ArrayList<Task> history = new ArrayList<Task>();

        while (!a.equals("bye")) {
            if (a.equals("list")) {
                System.out.println("Here are the tasks in your list:");

                int length = history.size();
                for (int i = 0; i < length; i++) {
                    System.out.println(String.valueOf(i + 1) + ". " + history.get(i));
                }
                a = sc.nextLine();
                b = a.split(" ", 2);

            } else if (b[0].equals("done")) {
                int taskIndex = Integer.valueOf(b[1]);
                history.get(taskIndex - 1).Done();
                System.out.println("Nice! I have marked this task as done!");
                System.out.println(history.get(taskIndex - 1));

                a = sc.nextLine();
                b = a.split(" ", 2);
            } else {
                System.out.println(a);
                Task todo = new Task(a);
                history.add(todo);
                a = sc.nextLine();
                b = a.split(" ", 2);
            }
        }
            System.out.println("Bye! Hope to see you again soon!");
            sc.close();

    }
}
