import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> taskList;

    public static void main(String[] args) {
        /* // in case we might need the logo later
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
         */
        // greet the user
        String greeting = "\t Hello! I'm Duke\n" +
                "\t What can I do for you?\n" ;
        System.out.println(reply(greeting));
        taskList = new ArrayList<>();
        // listen to user input
        Scanner scan = new Scanner(System.in);
        String command = scan.nextLine();
        while (!command.equals("bye")) {
            if (command.equals("list")) {
                System.out.println(reply(list()));
            } else if (command.startsWith("done ")) {
                int index = command.toCharArray()[command.length()-1] - '0' - 1;
                System.out.println(reply(done(index)));
            } else { // add the task
                Task t = new Task(command);
                System.out.println(reply(add(command)));
            }
            command = scan.nextLine();
        }
        // farewell the user
        String bye = "\t Bye. Hope to see you again soon!\n";
        System.out.println(reply(bye));
    }

    private static String reply(String content) {
        // wrap the reply in a divider
        String divider = "\t____________________________________________________________";
        return divider + "\n" + content + divider;
    }

    private static String echo(String input) {
        // echo back user commands
        return String.format("\t %s\n", input);
    }

    private static String add(String input) {
        // add task to the taskList
        taskList.add(new Task(input));
        return "\t added: " + input + "\n";
    }

    private static String list() {
        // list out all tasks
        int count = 1;
        StringBuilder reply = new StringBuilder();
        for (Task task : taskList) {
            reply.append(String.format("\t %d.[%s] %s\n", count, task.getStatusIcon(), task));
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
        return String.format("\t Nice! I've marked this task as done: \n" +
                "\t   [X] %s\n", t);
    }
}


