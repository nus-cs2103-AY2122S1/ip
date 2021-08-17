import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Presentation pst = new Presentation();
        List<Task> taskList = new ArrayList<>();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        pst.respondWith("Hello! I'm Duke. \nWhat can I do for you?");

        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        pst.enterCommand();
        String command = sc.next();
        command = command.trim();

        while (!command.equals("bye")) {
            if (command.equals("list")) {
                // If command is list, print out all current tasks
                pst.printTaskList(taskList);
            } else if (command.startsWith("done")) {
                // If command is done, set the specified task to completed
                command = command.substring(4).replaceAll("\\s+", "");
                int position = Integer.parseInt(command) - 1;
                Task calledTask = taskList.remove(position);
                calledTask.markAsCompleted();
                taskList.add(position, calledTask);
                pst.respondWith("Nice! I've marked this task as done: \n" + calledTask);
            } else {
                // Else, add the task to the list
                pst.respondWith("Added: " + command);
                taskList.add(new Task(command));
            }
            pst.enterCommand();
            command = sc.next();
            command = command.trim();
        }

        sc.close();
        pst.addSpace();
        System.out.println("Program exiting... \nBye. Hope to see you again soon!\n");
    }
}
