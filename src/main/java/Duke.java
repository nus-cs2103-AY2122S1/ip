import java.util.*;

public class Duke {
    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<>();

        String logo = "\t ____        _        \n"
                + "\t|  _ \\ _   _| | _____ \n"
                + "\t| | | | | | | |/ / _ \\\n"
                + "\t| |_| | |_| |   <  __/\n"
                + "\t|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello from\n" + logo);
        System.out.println("\tHello! I'm Duke" + "\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________");

        Scanner sc = new Scanner(System.in);
        String command = sc.next();
        String arguments = sc.nextLine().stripLeading();

        while (!command.equals("bye")) {
            if (command.equals("list")) {
                System.out.println("\t____________________________________________________________");
                System.out.println("\tHere are the tasks in your list:");
                if (tasks.isEmpty()) {
                    System.out.println("\t  You currently have no tasks. Why not add a task?");
                } else {
                    for (int i = 0; i < tasks.size(); i++) {
                        Task currTask = tasks.get(i);
                        System.out.println("\t" + (i + 1) + ". " + currTask);
                    }
                }
                System.out.println("\t____________________________________________________________");
            } else if (command.equals("done")) {
                int index = Integer.parseInt(arguments);
                if (index < 1 || index > tasks.size()) {
                    System.out.println("\t____________________________________________________________");
                    System.out.println("\tThe index you entered is invalid. Please try again.");
                    System.out.println("\t____________________________________________________________");
                } else {
                    Task taskToBeMarked = tasks.get(index - 1);
                    taskToBeMarked.markTaskAsDone();
                    System.out.println("\t____________________________________________________________");
                    System.out.println("\tNice! I've marked this task as done:");
                    System.out.println("\t  " + taskToBeMarked);
                    System.out.println("\t____________________________________________________________");
                }
            } else {
                System.out.println("\t____________________________________________________________");
                System.out.println("\tGot it. I'v added this task:");
                if (command.equals("todo")) {
                    Todo newTask = new Todo(arguments);
                    tasks.add(newTask);
                    System.out.println("\t  " + newTask);
                } else if (command.equals("deadline")) {
                    String[] argArr = arguments.split("/by");
                    Deadline newTask = new Deadline(argArr[0], argArr[1]);
                    tasks.add(newTask);
                    System.out.println("\t  " + newTask);
                } else if (command.equals("event")) {
                    String[] argArr = arguments.split("/at");
                    Event newTask = new Event(argArr[0], argArr[1]);
                    tasks.add(newTask);
                    System.out.println("\t  " + newTask);
                }
                System.out.println(String.format("\tNow you have %s " +
                        (tasks.size() == 1 ? "task" : "tasks" )
                        + " in the list.", tasks.size()));
                System.out.println("\t____________________________________________________________");
            }
            command = sc.next();
            arguments = sc.nextLine().stripLeading();
        }

        System.out.println("\t____________________________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");
    }
}
