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
                int index = Integer.parseInt(command.split(" ")[1]) - 1;
                System.out.println(reply(done(index)));
            } else { // add the task
                String taskType = command.split(" ")[0];
                System.out.println(reply(add(command, taskType)));
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

    private static String list() {
        // list out all tasks
        int count = 1;
        StringBuilder reply = new StringBuilder();
        for (Task task : taskList) {
            reply.append(String.format("\t %d.%s\n", count, task));
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
                "\t   %s\n", t);
    }

    private static String add(String input, String type) {
        Task t;
        int delimiter = input.indexOf("/");
        // add task to the taskList
        switch (type) {
            case "todo" :
                taskList.add(t = new Todo(input.substring(4)));
                break;
            case "deadline" :
                taskList.add(t = new Deadline(input.substring(8, delimiter), input.substring(delimiter+4)));
                break;
            case "event" :
                taskList.add(t = new Event(input.substring(5, delimiter), input.substring(delimiter+4)));
                break;
            default :
                t = new Task(input);
        }
        // reply
        return "\t Got it. I've added this task: \n" +
                String.format("\t   %s\n", t) +
                String.format("\t Now you have %d tasks in the list.\n", taskList.size());
    }
}


