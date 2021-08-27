import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        TaskList userList;
        try {
            userList = TaskList.load();
        } catch (Exception exception) {
            System.out.println(exception);
            System.out.println("Task list not found.");
            userList = new TaskList();
            System.out.println("New task list created");
        }

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);

        while (true) {
            String inp = sc.nextLine();
            if (inp.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (inp.equals("list")) { //check if command is "list"
                userList.list();
            } else if (inp.startsWith("done ")) { //check if command is "done"
                int number = Integer.parseInt(inp.substring(5));
                userList.markDone(number);
            } else if (inp.startsWith("delete ")) { //check if command is "delete"
                int number = Integer.parseInt(inp.substring(7));
                userList.remove(number);
            } else if (inp.startsWith("todo ")) { //check if command is "todo"
                if (inp.length() == 5) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                Task task = new Todo(inp.substring(5));
                userList.add(task);
            } else if (inp.startsWith("deadline ")) { //check if command is "deadline"
                int index = inp.indexOf("/by ");
                Task task = new Deadline(inp.substring(9, index), inp.substring(index + 4));
                userList.add(task);
            } else if (inp.startsWith("event ")) { //check if command is "event"
                int index = inp.indexOf("/at "); // find index of "/at "
                Task task = new Event(inp.substring(6, index), inp.substring(index + 4));
                userList.add(task);
            } else {
                // Invalid input
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }
}
