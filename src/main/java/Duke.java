import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> taskList;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        // greet the user
        String greeting = "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" ;
        System.out.println(reply(greeting));
        taskList = new ArrayList<>();
        // listen to user input
        Scanner scan = new Scanner(System.in);
        String command = scan.nextLine();
        while (!command.equals("bye")) {
            if (command.equals("list")) {
                System.out.println(reply(list()));
            } else if (command.startsWith("done ")) {
                int index = Integer.parseInt(command.split(" ")[1]) - 1;
                System.out.println(reply(done(index)));
            } else if (command.startsWith("delete ")) {
                int index = Integer.parseInt(command.split(" ")[1]) - 1;
                System.out.println(reply(delete(index)));
            } else { // add the task
                try {
                    System.out.println(reply(add(command)));
                } catch (InvalidCommandException e) {
                    System.out.println(reply(e.getMessage()));
                }
            }
            command = scan.nextLine();
        }
        // farewell the user
        String bye = "     Bye. Hope to see you again soon!\n";
        System.out.println(reply(bye));
    }

    private static String reply(String content) {
        // wrap the reply in a divider
        String divider = "    ____________________________________________________________";
        return divider + "\n" + content + divider;
    }

    private static String list() {
        // list out all tasks
        int count = 1;
        StringBuilder reply = new StringBuilder("     Here are the tasks in your list:\n");
        for (Task task : taskList) {
            reply.append(String.format("     %d.%s\n", count, task));
            count++;
        }
        return reply.toString();
    }

    private static String done(int index) {
        // retrieve task from TaskList
        Task t = taskList.get(index);
        // mark task as done
        t.markAsDone();
        // reply
        return String.format("     Nice! I've marked this task as done: \n" +
                "       %s\n", t);
    }

    private static String add(String input) throws InvalidCommandException {
        // String manipulations
        String[] check = input.split(" ");
        String type = check[0];
        String[] split = input.split(" /");
        // add task to the taskList based on its type
        Task t;
        switch (type) {
            case "todo" :
                if (check.length == 1) throw new EmptyDescriptionError("todo");
                taskList.add(t = new Todo(input.substring(5)));
                break;
            case "deadline" :
                if (check.length == 1) throw new EmptyDescriptionError("deadline");
                taskList.add(t = new Deadline(split[0].substring(9), split[1].substring(3)));
                break;
            case "event" :
                if (check.length == 1) throw new EmptyDescriptionError("event");
                taskList.add(t = new Event(split[0].substring(6), split[1].substring(3)));
                break;
            default :
                t = new Task(input); // for tasks other than todo, deadline, event in future
                throw new UnknownCommandError("");
        }
        // reply
        return "     Got it. I've added this task: \n" +
                String.format("       %s\n", t) +
                String.format("     Now you have %d tasks in the list.\n", taskList.size());
    }

    private static String delete(int index) {
        // retrieve the task
        Task t = taskList.get(index);
        // remove the task
        taskList.remove(index);
        // reply
        return "     Noted. I've removed this task: \n" +
                String.format("       %s\n", t) +
                String.format("     Now you have %d tasks in the list.\n", taskList.size());
    }
}


