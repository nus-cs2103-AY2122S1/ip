import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Duke {

    // List to store all user inputs
    private TaskList taskList;
    private final Storage storage;

    public Duke(){
        storage = new Storage();
        taskList = new TaskList();
        taskList.loadFromStorage(storage.load());
    }
    private static final String LINE = "    ____________________________________________________________";
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        // Greetings
        System.out.println(LINE);
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println(LINE);


        // Take in user input
        Duke duke = new Duke();
        TaskList taskList = duke.taskList;
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String str = sc.nextLine();
            boolean taskAdded = false;
            if (!str.equals("list") && !str.contains("done")) {
                if (str.contains("todo")) {
                    if (str.length() == 4) {
                        System.out.println(LINE);
                        System.out.println("     ☹ OOPS!!! The description of a todo cannot be empty.");
                        System.out.println(LINE);
                        continue;
                    }
                    taskList.addTask(new ToDo(str.substring(5), false));
                    taskAdded = true;
                } else if (str.contains("deadline")) {
                    String description = "";
                    String by = "";
                    boolean trigger = false;
                    String[] arr = str.split(" ");
                    for (String s : arr) {
                        if (s.equals("deadline")) continue;
                        if (s.equals("/by")) {
                            trigger = true;
                            continue;
                        }
                        if (!trigger) description += s + " ";
                        else by += s + " ";
                    }
                    if (description.equals("")) {
                        System.out.println(LINE);
                        System.out.println("     ☹ OOPS!!! The description of a deadline cannot be empty.");
                        System.out.println(LINE);
                    } else if (by.equals("")) {
                        System.out.println(LINE);
                        System.out.println("     ☹ OOPS!!! The by of a deadline cannot be empty.");
                        System.out.println(LINE);
                    }
                    description = description.substring(0, description.length() - 1);
                    by = by.substring(0, by.length() - 1);
                    LocalDateTime byLocalDateTime = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    taskList.addTask(new Deadline(description, false, byLocalDateTime));
                    taskAdded = true;
                } else if (str.contains("event")) {
                    String description = "";
                    String at = "";
                    boolean trigger = false;
                    String[] arr = str.split(" ");
                    for (String s : arr) {
                        if (s.equals("event")) continue;
                        if (s.equals("/at")) {
                            trigger = true;
                            continue;
                        }
                        if (!trigger) description += s + " ";
                        else at += s + " ";
                    }
                    if (description.equals("")) {
                        System.out.println(LINE);
                        System.out.println("     ☹ OOPS!!! The description of a event cannot be empty.");
                        System.out.println(LINE);
                    } else if (at.equals("")) {
                        System.out.println(LINE);
                        System.out.println("     ☹ OOPS!!! The at of a event cannot be empty.");
                        System.out.println(LINE);
                    }
                    description = description.substring(0, description.length() - 1);
                    at = at.substring(0, at.length() - 1);
                    LocalDateTime atLocalDateTime = LocalDateTime.parse(at, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    taskList.addTask(new Event(description, false, atLocalDateTime));
                    taskAdded = true;
                }
                if (taskAdded) {
                    System.out.println(LINE);
                    System.out.println("     Got it. I've added this task:");
                    System.out.println("       " + taskList.getTask(taskList.size()).getStatus());
                    System.out.println("     Now you have " + taskList.size() + " tasks in the list.");
                    System.out.println(LINE);
                    continue;
                }
            }
            if (str.equals("bye")) {
                System.out.println(LINE);
                System.out.println("     Bye. Hope to see you again soon!");
                System.out.println(LINE);
                break;
            } else if (str.equals("list")) {
                System.out.println(LINE);
                for (int i = 1; i <= taskList.size(); i++) {
                    System.out.println("     " + i + "." + taskList.getTask(i).getStatus());
                }
                System.out.println(LINE);
                //continue;
            } else if (str.contains("done")){
                int taskNumber = Integer.valueOf(str.substring(5));
                taskList.markAsDone(taskNumber);
                System.out.println(LINE);
                System.out.println("     Nice! I've marked this task as done: ");
                System.out.println("       " + taskList.getTask(taskNumber).getStatus());
                System.out.println(LINE);
                //continue;
            } else if (str.contains("delete")){
                int taskNumber = Integer.valueOf(str.substring(7));
                System.out.println(LINE);
                System.out.println("     Noted. I've removed this task: ");
                System.out.println("       " + taskList.deleteTask(taskNumber).getStatus());
                System.out.println("     Now you have " + taskList.size() + " tasks in the list.");
                System.out.println(LINE);
                //continue;
            }
            else {
                System.out.println(LINE);
                System.out.println("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println(LINE);
            }
        }
    }
}
