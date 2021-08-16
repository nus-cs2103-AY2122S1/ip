import java.util.*;

public class Duke {
    public static void main(String[] args) {
        List<Task> taskList = new ArrayList<>();

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
        String command = sc.nextLine();

        while (!command.equals("bye")) {
            if (command.equals("list")) {

                System.out.println("\t____________________________________________________________");
                System.out.println("\tHere are the tasks in your list:");
                if (taskList.isEmpty()) {
                    System.out.println("\t  You currently have no tasks. Why not add a task?");
                } else {
                    for (int i = 0; i < taskList.size(); i++) {
                        Task currTask = taskList.get(i);
                        System.out.println("\t" + (i + 1) + ". " + currTask);
                    }
                }
                System.out.println("\t____________________________________________________________");
            } else if (command.split(" ")[0].equals("done")) {
                int index = Integer.parseInt(command.split(" ")[1]);
                if (index < 1 || index > taskList.size()) {
                    System.out.println("\t____________________________________________________________");
                    System.out.println("\tThe index you entered is invalid. Please try again.");
                    System.out.println("\t____________________________________________________________");
                } else {
                    Task taskToBeMarked = taskList.get(index - 1);
                    taskToBeMarked.markTaskAsDone();
                    System.out.println("\t____________________________________________________________");
                    System.out.println("\tNice! I've marked this task as done:");
                    System.out.println("\t  " + taskToBeMarked);
                    System.out.println("\t____________________________________________________________");
                }
            } else {
                taskList.add(new Task(command));
                System.out.println("\t____________________________________________________________");
                System.out.println("\tadded: " + command);
                System.out.println("\t____________________________________________________________");
            }
            command = sc.nextLine();
        }

        System.out.println("\t____________________________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");
    }
}
