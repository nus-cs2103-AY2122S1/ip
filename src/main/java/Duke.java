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

        // Commands constants
        final String bye = "bye";
        final String list = "list";
        final String done = "done";
        final String todo = "todo";
        final String deadline = "deadline";
        final String event = "event";

        List<Task> userList = new ArrayList<>();

        while (true) {
            String inp = sc.nextLine();
            String[] parts = inp.split(" ");
            if (inp.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (inp.equals("list")) {
                int counter = 1;
                for (Task task:userList) {
                    System.out.println(counter + "." + task);
                    //System.out.println(counter + ".[" + task.getStatusIcon() + "] " + task);
                    counter++;
                }
            } else if (inp.startsWith("done ")) {
                //parts.length == 2 &&
                // Need to check for invalid input
                int index = Integer.parseInt(inp.substring(5)) - 1;
                Task task = userList.get(index);
                task.markDone();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(task);
                // System.out.println("[" + task.getStatusIcon() + "] " + task);
            } else if (inp.startsWith("todo ")) { //todo command
                Task task = new Todo(inp.substring(5));
                userList.add(task);
                System.out.println("added: " + task);
            } else if (inp.startsWith("deadline ")) { //deadline command
                int index = inp.indexOf("/by ");
                Task task = new Deadline(inp.substring(9, index), inp.substring(index + 4));
                userList.add(task);
                System.out.println("added: " + task);
            } else if (inp.startsWith("event ")) { //event command
                int index = inp.indexOf("/at "); // find index of "/at "
                Task task = new Event(inp.substring(6, index), inp.substring(index + 4));
                userList.add(task);
                System.out.println("added: " + task);
            } else {
                // To be phase out
                // userList.add(new Task(inp));
                // System.out.println("added: " + inp);
            }
        }
    }
}
