import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);

        List<Task> userList = new ArrayList<>();

        while (true) {
            String inp = sc.nextLine();
            if (inp.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (inp.equals("list")) { //check if command is "list"
                int counter = 1;
                for (Task task:userList) {
                    System.out.println(counter + "." + task);
                    counter++;
                }
            } else if (inp.startsWith("done ")) { //check if command is "done"
                int index = Integer.parseInt(inp.substring(5)) - 1;
                Task task = userList.get(index);
                task.markDone();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(task);
            } else if (inp.startsWith("delete ")) { //check if command is "delete"
                int index = Integer.parseInt(inp.substring(7)) - 1;
                Task task = userList.get(index);
                userList.remove(index);
                System.out.println("Noted. I've removed this task: ");
                System.out.println(task);
                System.out.println("Now you have " + userList.size() + " tasks in the list.");
            } else if (inp.startsWith("todo ")) { //check if command is "todo"
                if (inp.length() == 5) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                Task task = new Todo(inp.substring(5));
                userList.add(task);
                System.out.println("Got it. I've added this task: ");
                System.out.println(task);
                System.out.println("Now you have " + userList.size() + " tasks in the list.");
            } else if (inp.startsWith("deadline ")) { //check if command is "deadline"
                int index = inp.indexOf("/by ");
                Task task = new Deadline(inp.substring(9, index), inp.substring(index + 4));
                userList.add(task);
                System.out.println("Got it. I've added this task: ");
                System.out.println(task);
                System.out.println("Now you have " + userList.size() + " tasks in the list.");
            } else if (inp.startsWith("event ")) { //check if command is "event"
                int index = inp.indexOf("/at "); // find index of "/at "
                Task task = new Event(inp.substring(6, index), inp.substring(index + 4));
                userList.add(task);
                System.out.println("Got it. I've added this task: ");
                System.out.println(task);
                System.out.println("Now you have " + userList.size() + " tasks in the list.");
            } else {
                // Invalid input
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }
}
