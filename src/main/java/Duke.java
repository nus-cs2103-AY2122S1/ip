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

            System.out.println(divider);
            if (str.equals("bye")) {
                exit = true;
                System.out.println("Bye. Hope to see you again soon!");
            } else if (str.equals("list")) {
                int count = 1;
                System.out.println("Here are the tasks in your list:");
                for (Task t : request) {
                    System.out.println(count + "." + t.getTask());
                    count += 1;
                }
            } else if (str.contains("done")) {
                int index = Integer.parseInt(str.substring(5)) - 1;
                System.out.println(request.get(index).done());
            } else {
                String[] words = str.split(" ", 2);
                String type = words[0];
                String text = words[1];
                Task task;

                if (type.equals("todo")) {
                    task = new Todo(text, "");
                } else if (type.equals("deadline")) {
                    String[] splitted = text.split("/by ", 2);
                    text = splitted[0];
                    String deadline = "(by: " + splitted[1] + ")";
                    task = new Deadline(text, deadline);
                } else {
                    String[] splitted = text.split("/at ", 2);
                    text = splitted[0];
                    String time = "(at: " + splitted[1] + ")";
                    task = new Event(text, time);
                }
                request.add(task);
                System.out.println("Got it. I've added this task: \n" + task.getTask() + "\nNow you have " + request.size() + " tasks in the list.");
            }
            System.out.println(divider);
        }
    }
}
