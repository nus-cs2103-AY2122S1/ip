import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");

        Scanner sc = new Scanner(System.in);
        String input = "";
        TaskList ls = new TaskList(new ArrayList<Task>());

        while (true) {
            input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                if (ls.getSize() == 0) {
                    System.out.println("There are currently no tasks in your list.");
                } else {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < ls.getSize(); i++) {
                        System.out.println((i+1) + "." + ls.getTask(i));
                    }
                }
            } else if (input.startsWith("done")) {
                int arrIndex = Integer.valueOf(input.substring(5)) - 1;
                Task task = ls.getTask(arrIndex);
                task.markAsDone();
                System.out.println(task.markedAsDoneToString());
            } else {
                if (input.startsWith("todo")) {
                    String taskDesc = input.replaceFirst("^todo ", "");
                    Todo tTask = new Todo(taskDesc);
                    ls.addTask(tTask);
                    System.out.println(ls.addTaskToString(tTask));
                } else if (input.startsWith("deadline")) {
                    String taskDesc = input.replaceFirst("^deadline ", "").split(" /")[0];
                    String deadline = input.substring(input.indexOf("/by") + 4);
                    Deadline dTask = new Deadline(taskDesc, deadline);
                    ls.addTask(dTask);
                    System.out.println(ls.addTaskToString(dTask));
                } else if (input.startsWith("event")) {
                    String taskDesc = input.replaceFirst("^event ", "").split(" /")[0];
                    String eventTime = eventTime = input.substring(input.indexOf("/at") + 4);
                    Event eTask = new Event(taskDesc, eventTime);
                    ls.addTask(eTask);
                    System.out.println(ls.addTaskToString(eTask));
                }
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
