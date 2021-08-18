import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        int numOfTasks = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm Irving.");
        System.out.println("    What can I do for you?");
        System.out.println("    ____________________________________________________________");
        String task = sc.nextLine();
        while (!task.equals("bye")) {
            if (task.length() >= 6 && task.substring(0, 4).equals("done")) {
                int itemDone = Integer.parseInt(task.substring(5));
                tasks[itemDone - 1].done = true;
                System.out.println("    ____________________________________________________________");
                System.out.println("    Nice! I've marked this task as done: ");
                System.out.println("      " + tasks[itemDone - 1].toString());
                System.out.println("    ____________________________________________________________");
                task = sc.nextLine();
            } else if (task.equals("list")) {
                System.out.println("    ____________________________________________________________");
                System.out.println("    Here are the tasks in your list:");
                for (int i = 1; i <= numOfTasks; i++) {
                    System.out.println("    " + i + "." + tasks[i - 1].toString());
                }
                System.out.println("    ____________________________________________________________");
                task = sc.nextLine();
            } else {
                tasks[numOfTasks] = new Task(task);
                numOfTasks++;
                System.out.println("    ____________________________________________________________");
                System.out.println("    added: " + task);
                System.out.println("    ____________________________________________________________");
                task = sc.nextLine();
            }
        }
        System.out.println("    ____________________________________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }
}
