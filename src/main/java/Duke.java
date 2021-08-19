import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Task[] tasks = new Task[100];
        int numOfTasks = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm Irving.");
        System.out.println("    What can I do for you?");
        System.out.println("    ____________________________________________________________");
        String task = sc.nextLine();
        while (!task.equals("bye")) {
            System.out.println("    ____________________________________________________________");
            if (task.contains("todo")) {
                String taskName = task.substring(5);
                tasks[numOfTasks++] = new Todo(taskName);
                System.out.println("     Got it. I've added this task:");
                System.out.println("      " + tasks[numOfTasks - 1].toString());
                System.out.println("     Now you have " + numOfTasks + " tasks in the list.");
            } else if (task.contains("deadline")) {
                int position = task.indexOf('/');
                String taskName = task.substring(9, position - 1);
                String deadlineTime = task.substring(position + 4);
                tasks[numOfTasks++] = new Deadline(taskName, deadlineTime);
                System.out.println("     Got it. I've added this task:");
                System.out.println("      " + tasks[numOfTasks - 1].toString());
                System.out.println("     Now you have " + numOfTasks + " tasks in the list.");
            } else if (task.contains("event")) {
                int position = task.indexOf('/');
                String taskName = task.substring(6, position - 1);
                String eventTime = task.substring(position + 4);
                tasks[numOfTasks++] = new Event(taskName, eventTime);
                System.out.println("     Got it. I've added this task:");
                System.out.println("      " + tasks[numOfTasks - 1].toString());
                System.out.println("     Now you have " + numOfTasks + " tasks in the list.");
            } else if (task.contains("done")) {
                int itemDone = Integer.parseInt(task.substring(5));
                tasks[itemDone - 1].done = true;
                System.out.println("    Nice! I've marked this task as done:");
                System.out.println("      " + tasks[itemDone - 1].toString());
            } else if (task.equals("list")) {
                System.out.println("    Here are the tasks in your list:");
                for (int i = 1; i <= numOfTasks; i++) {
                    System.out.println("    " + i + "." + tasks[i - 1].toString());
                }
            } else {
                tasks[numOfTasks] = new Task(task);
                numOfTasks++;
                System.out.println("    added: " + task);
            }
            System.out.println("    ____________________________________________________________");
            task = sc.nextLine();
        }
        System.out.println("    ____________________________________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }
}
