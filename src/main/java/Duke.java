import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<String> taskList;

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
            } else { // add the task
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
        taskList.add(input);
        return "\t added: " + input + "\n";
    }

    private static String list() {
        // list out all tasks
        int count = 1;
        StringBuilder reply = new StringBuilder();
        for (String task : taskList) {
            reply.append(String.format("\t %d. %s\n", count, task));
            count++;
        }
        return reply.toString();
    }
}


