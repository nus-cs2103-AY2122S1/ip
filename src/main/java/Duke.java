import java.util.*;
public class Duke {
    public static void main(String[] args) throws DukeException {
        Scanner sc = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String line = "____________________________________________________________\n";
        System.out.println(line + "Hello I'm Duke\nWhat can I do for you?\n" + line);

        ArrayList<Task> tasks = new ArrayList<>(100);
        int ctr = 0;
        String command = sc.nextLine();

        while (!command.equals("bye")) {
            try {
                if (command.contains("done")) {
                    if (command.indexOf(" ") == -1) {
                        throw new DukeException("OOPS!! done needs the index of the task.");
                    }
                    int idx = Integer.parseInt(command.split(" ")[1]) - 1;
                    tasks.get(idx).updateStatus();
                    System.out.printf("%sNice! I've marked this task as done:\n%s\n%s\n", line, tasks.get(idx), line);
                } else if (command.equals("list")) {
                    System.out.println(line + "Here are the tasks in your list:");
                    for (int i = 0; i < ctr; i++) {
                        System.out.printf("%d.%s\n", i + 1, tasks.get(i));
                    }
                    System.out.println(line);
                }else if (command.contains("delete")) {
                    if (command.indexOf(" ") == -1) {
                        throw new DukeException("OOPS!! delete needs the index of the task.");
                    }
                    int idx = Integer.parseInt(command.split(" ")[1]) - 1;
                    Task t = tasks.remove(idx);
                    System.out.printf("%sNoted. I've removed this task:\n%s\nNow you have %d tasks in the list\n%s\n", line, t, ctr - 1, line);
                    ctr--;
                } else if (command.contains("todo")) {
                    if (command.indexOf(" ") == -1) {
                        throw new DukeException("OOPS!! The description of a todo cannot be empty.");
                    }
                    int taskIdxStart = command.indexOf(" ") + 1;
                    String task = command.substring(taskIdxStart);
                    tasks.add(new TodoTask(task));
                    System.out.printf("%sGot it. I've added this task:\n%s\nNow you have %d tasks in the list\n%s\n", line, tasks.get(ctr), ctr + 1, line);
                    ctr++;
                } else if (command.contains("deadline")) {
                    if (command.indexOf(" ") == -1) {
                        throw new DukeException("OOPS!! The description of a deadline cannot be empty.");
                    }
                    int taskIdxStart = command.indexOf(" ") + 1;
                    int timeIdxStart = command.indexOf("/");
                    String task = command.substring(taskIdxStart, timeIdxStart - 1);
                    String time = command.substring(timeIdxStart + 4);
                    tasks.add(new DeadlineTask(task, time));
                    System.out.printf("%sGot it. I've added this task:\n%s\nNow you have %d tasks in the list\n%s\n", line, tasks.get(ctr), ctr + 1, line);
                    ctr++;
                } else if (command.contains("event")) {
                    if (command.indexOf(" ") == -1) {
                        throw new DukeException("OOPS!! The description of an event cannot be empty.");
                    }
                    int taskIdxStart = command.indexOf(" ") + 1;
                    int timeIdxStart = command.indexOf("/");
                    String task = command.substring(taskIdxStart, timeIdxStart - 1);
                    String time = command.substring(timeIdxStart + 4);
                    tasks.add(new EventTask(task, time));
                    System.out.printf("%sGot it. I've added this task:\n%s\nNow you have %d tasks in the list\n%s\n", line, tasks.get(ctr), ctr + 1, line);
                    ctr++;
                } else {
                    throw new DukeException("OOPS!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(line + e.getMessage() + "\n"+ line);
            }
            command = sc.nextLine();
        }

        System.out.println(line + "Bye. Hope to see you again soon!\n" + line);
    }
}
