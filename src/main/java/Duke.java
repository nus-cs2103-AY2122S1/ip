import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // Task[] arr = new Task[100];
        List<Task> arr = new ArrayList<>();
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
                    System.out.println("  " + (i + 1) + "." + arr.get(i).toString());
                }
                System.out.println("____________________________________________________________");
            } else if (input.startsWith("done ")) {
                int task = Integer.parseInt(input.split(" ")[1]) - 1;
                arr.get(task).markAsDone();
                System.out.println("____________________________________________________________");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("    " + arr.get(task).toString());
                System.out.println("____________________________________________________________");
            } else if (input.startsWith("todo")) {
                try {
                    if (input.split(" ").length == 1) {
                        throw new DukeException("____________________________________________________________\n" +
                                "☹ OOPS!!! The description of a todo cannot be empty.\n" +
                                "____________________________________________________________");
                    }
                    String task = input.split(" ")[1];
                    arr.add(new Todo(task));
                    count++;
                    String s = "";
                    if (count > 1 && count != 0) {
                        s = "s";
                    }
                    String reply = "____________________________________________________________\n" +
                            "Got it. I've added this task:\n  " +
                            arr.get(count-1) + "\n" +
                            "Now you have " + count + " task" + s + " in the list.\n" +
                            "____________________________________________________________";
                    System.out.println(reply);
                } catch (DukeException e) {
                    System.out.println(e);
                }
            } else if (input.startsWith("deadline")) {
                String[] n = input.split(" ", 2)[1].split(" /by ");
                arr.add(new Deadline(n[0], n[1]));
                count++;
                String s = "";
                if (count > 1 && count != 0) {
                    s = "s";
                }
                String reply = "____________________________________________________________\n" +
                        "Got it. I've added this task:\n  " +
                        arr.get(count-1) + "\n" +
                        "Now you have " + count + " task" + s + " in the list.\n" +
                        "____________________________________________________________";
                System.out.println(reply);
            } else if (input.startsWith("event")) {
                String[] n = input.split(" ", 2)[1].split(" /at ");
                arr.add(new Event(n[0], n[1]));
                count++;
                String s = "";
                if (count > 1 && count != 0) {
                    s = "s";
                }
                String reply = "____________________________________________________________\n" +
                        "Got it. I've added this task:\n  " +
                        arr.get(count-1) + "\n" +
                        "Now you have " + count + " task" + s + " in the list.\n" +
                        "____________________________________________________________";
                System.out.println(reply);
            } else if (input.startsWith("delete")) {
                int pos = Integer.parseInt(input.split(" ")[1]);
                Task task = arr.get(pos-1);
                arr.remove(pos-1);
                count--;
                String s = "";
                if (count > 1 && count != 0) {
                    s = "s";
                }
                String reply = "____________________________________________________________\n" +
                        "Noted. I've removed this task:\n  " +
                        task + "\n" +
                        "Now you have " + count + " task" + s + " in the list.\n" +
                        "____________________________________________________________";
                System.out.println(reply);
            } else {
                System.out.println(new DukeException("____________________________________________________________\n" +
                        "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                        "____________________________________________________________"));
            }
            /* else {
                arr[count] = new Task(input);
                count++;
                String s = "";
                if (count > 1 && count != 0) {
                    s = "s";
                }
                String reply = "____________________________________________________________\n" +
                        "Got it. I've added this task:\n  " +
                        arr[count-1] + "\n" +
                        "Now you have " + count + " task" + s + " in the list.\n" +
                        "____________________________________________________________";
                System.out.println(reply);
            }*/
        }
    }
}

