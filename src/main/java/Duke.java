import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Task[] arr = new Task[100];
        int count = 0;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        // System.out.println("Hello from\n" + logo);
        String greeting = "____________________________________________________________\n" +
                "Hello! I'm Duke\n" +
                "What can I do for you?\n" +
                "____________________________________________________________\n";
        System.out.println(greeting);
        Scanner myObj = new Scanner(System.in);
        while (true) {
            String input = myObj.nextLine();
            if (input.equals("bye")) {
                String bye = "____________________________________________________________\n" +
                        "Bye. Hope to see you again soon!\n" +
                        "____________________________________________________________";
                System.out.println(bye);
                return;
            } else if (input.equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < count; i++) {
                    System.out.println(i + 1 + "." + arr[i].toString());
                }
                System.out.println("____________________________________________________________");
            } else if (input.startsWith("done ")) {
                int task = Integer.parseInt(input.split(" ")[1]) - 1;
                arr[task].markAsDone();
                System.out.println("____________________________________________________________");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("    " + arr[task].toString());
                System.out.println("____________________________________________________________");
            } else if (input.startsWith("todo ")) {
                String task = input.split(" ")[1];
                arr[count] = new Todo(task);
                count++;
                String s = "";
                if (count > 1) {
                    s = "s";
                }
                String reply = "____________________________________________________________\n" +
                        "Got it. I've added this task:\n  " +
                        arr[count-1] + "\n" +
                        "Now you have " + count + " task" + s + " in the list.\n" +
                        "____________________________________________________________";
                System.out.println(reply);
            } else if (input.startsWith("deadline ")) {
                String[] n = input.split(" ", 2)[1].split(" /by ");
                arr[count] = new Deadline(n[0], n[1]);
                count++;
                String s = "";
                if (count > 1) {
                    s = "s";
                }
                String reply = "____________________________________________________________\n" +
                        "Got it. I've added this task:\n  " +
                        arr[count-1] + "\n" +
                        "Now you have " + count + " task" + s + " in the list.\n" +
                        "____________________________________________________________";
                System.out.println(reply);
            } else if (input.startsWith("event ")) {
                String[] n = input.split(" ", 2)[1].split(" /at ");
                arr[count] = new Event(n[0], n[1]);
                count++;
                String s = "";
                if (count > 1) {
                    s = "s";
                }
                String reply = "____________________________________________________________\n" +
                        "Got it. I've added this task:\n  " +
                        arr[count-1] + "\n" +
                        "Now you have " + count + " task" + s + " in the list.\n" +
                        "____________________________________________________________";
                System.out.println(reply);
            } else {
                arr[count] = new Task(input);
                count++;
                String s = "";
                if (count > 1) {
                    s = "s";
                }
                String reply = "____________________________________________________________\n" +
                        "Got it. I've added this task:\n  " +
                        arr[count-1] + "\n" +
                        "Now you have " + count + " task" + s + " in the list.\n" +
                        "____________________________________________________________";
                System.out.println(reply);
            }
        }
    }
}

