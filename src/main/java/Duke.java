import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // Initial values
        String sepLine = "____________________________________________________________";
        boolean isRunning = true;
        Scanner sc = new Scanner(System.in);

        String start = "Hello! I'm Duke. \n"
                + "What can I do for you? \n"
                + sepLine;

        ArrayList<Task> taskList = new ArrayList<>();

        System.out.println(start);

        // Main loop for commands
        while (isRunning) {
            String[] next = sc.nextLine().split(" ", 2);
            String command = next[0];
            if (command.equals("bye")) {
                System.out.println(sepLine + "\n" + "Bye. Hope to see you again soon!" + "\n" + sepLine);
                isRunning = false;
            } else if (command.equals("list")) {
                System.out.println(sepLine);
                System.out.println("These are your tasks! \n");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.print(i + 1 + ". " + taskList.get(i) + "\n");
                }
                System.out.println(sepLine);
            } else if (command.equals("done")) {
                // Mark a task as done
                // We assume the command is of the form "done xxx" where xxx is an integer
                System.out.println(sepLine);
                Integer taskNum = Integer.parseInt(next[1]);
                Task taskToComplete = taskList.get(taskNum - 1);
                taskToComplete.markAsDone();
                System.out.println("The task has been marked as done!");
                System.out.println(taskToComplete);
                System.out.println(sepLine);
            } else if (command.equals("todo")){
                // Add a to-do to the task list
                Todo todo = new Todo(next[1]);
                taskList.add(todo);
                System.out.println(sepLine + "\n added: " + todo + "\n");
                System.out.println("You now have " + taskList.size() + " tasks");
                System.out.println(sepLine);
            } else if (command.equals("deadline")){
                // Add a deadline to the task list
                String[] text = next[1].split("/by ");
                String desc = text[0];
                String dueDate = text[1];
                Deadline dl = new Deadline(desc, dueDate);
                taskList.add(dl);
                System.out.println(sepLine + "\n added: " + dl + "\n");
                System.out.println("You now have " + taskList.size() + " tasks");
                System.out.println(sepLine);
            } else if (command.equals("event")){
                // Add an event to the task list
                String[] text = next[1].split("/at ");
                String desc = text[0];
                String time = text[1];
                Event event = new Event(desc, time);
                taskList.add(event);
                System.out.println(sepLine + "\n added: " + event + "\n");
                System.out.println("You now have " + taskList.size() + " tasks");
                System.out.println(sepLine);
            } else {
                System.out.println(sepLine + "\n I did not understand that command."
                        + "Type help for more info \n" + sepLine);
            }
        }
        sc.close();
    }
}
