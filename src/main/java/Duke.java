import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Duke: Hello from\n" + logo);
        String divider = "____________________________________________________________";
        System.out.println(divider);
        System.out.println("Duke: Hello! I'm Duke\nWhat can I do for you?");
        System.out.println(divider);

        boolean exit = false;
        ArrayList<Task> request = new ArrayList<Task>();

        while (!exit) {
            System.out.print("You: ");
            Scanner sc = new Scanner(System.in);
            String str = sc.nextLine();
            Task task = new Task(str);

            System.out.println(divider);
            if (str.equals("bye")) {
                exit = true;
                System.out.println("Bye. Hope to see you again soon!");
            } else if (str.equals("list")) {
                int count = 1;
                for (Task s : request) {
                    System.out.println(s.getTask());
                    count += 1;
                }
            } else if (str.contains("done")) {
                int index = Integer.parseInt(str.substring(5)) - 1;
                System.out.println(request.get(index).done());
            } else {
                request.add(task);
                System.out.println("added: " + str);
            }
            System.out.println(divider);
        }


    }
}
