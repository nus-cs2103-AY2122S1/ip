import java.util.ArrayList;
import java.util.Scanner;

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

        try {
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
                            System.out.println((i + 1) + "." + ls.getTask(i).toString());
                        }
                    }
                } else if (input.startsWith("done")) {
                    if (input.equals("done") || input.equals("done ")) {
                        throw new DukeException("An index must follow after the command word 'done'.");
                    } else {
                        int arrIndex = Integer.valueOf(input.substring(5)) - 1;
                        Task task = ls.getTask(arrIndex);
                        task.markAsDone();
                        System.out.println(task.markedAsDoneToString());
                    }
                } else {
                    if (input.startsWith("todo")) {
                        String taskDesc = input.replaceFirst("^todo", "");
                        Todo tTask = new Todo(taskDesc);
                        ls.addTask(tTask);
                        System.out.println(ls.addTaskToString(tTask));
                    } else if (input.startsWith("deadline")) {
                        String taskDesc = input.replaceFirst("^deadline", "").split(" /")[0];
                        String deadline = "";
                        if (input.contains("/by")) {
                            deadline = input.substring(input.indexOf("/by") + 3);
                        }
                        Deadline dTask = new Deadline(taskDesc, deadline);
                        ls.addTask(dTask);
                        System.out.println(ls.addTaskToString(dTask));
                    } else if (input.startsWith("event")) {
                        String taskDesc = input.replaceFirst("^event", "").split(" /")[0];
                        String eventTime = "";
                        if (input.contains("/at")) {
                            eventTime = input.substring(input.indexOf("/at") + 3);
                        }
                        Event eTask = new Event(taskDesc, eventTime);
                        ls.addTask(eTask);
                        System.out.println(ls.addTaskToString(eTask));
                    } else {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                }
            }
            System.out.println("Bye. Hope to see you again soon!");

        } catch(DukeException e) {
            System.out.println(e.getMessage());
        }
    }

}
