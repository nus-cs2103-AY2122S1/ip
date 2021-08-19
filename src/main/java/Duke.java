import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public enum inputTypes {
        BYE("bye"), TODO("todo"), DEADLINE("deadline"), EVENT("event"), LIST("list"), DONE("done"), DELETE("delete");

        private String value;

        inputTypes (String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        List<Task> tasks = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm Irving.");
        System.out.println("    What can I do for you?");
        System.out.println("    ____________________________________________________________");
        String task = sc.nextLine();
        while (!task.equals(inputTypes.BYE.getValue())) {
            try {
                System.out.println("    ____________________________________________________________");
                if (task.contains(inputTypes.TODO.getValue())) {
                    if (task.length() <= 5) {
                        throw new Exception("todoEmpty");
                    }
                    String taskName = task.substring(5);
                    tasks.add(new Todo(taskName));
                    System.out.println("     Got it. I've added this task:");
                    System.out.println("      " + tasks.get(tasks.size() - 1).toString());
                    System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
                } else if (task.contains(inputTypes.DEADLINE.getValue())) {
                    int position = task.indexOf('/');
                    String taskName = task.substring(9, position - 1);
                    String deadlineTime = task.substring(position + 4);
                    tasks.add(new Deadline(taskName, deadlineTime));
                    System.out.println("     Got it. I've added this task:");
                    System.out.println("      " + tasks.get(tasks.size() - 1).toString());
                    System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
                } else if (task.contains(inputTypes.EVENT.getValue())) {
                    int position = task.indexOf('/');
                    String taskName = task.substring(6, position - 1);
                    String eventTime = task.substring(position + 4);
                    tasks.add(new Event(taskName, eventTime));
                    System.out.println("     Got it. I've added this task:");
                    System.out.println("      " + tasks.get(tasks.size() - 1).toString());
                    System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
                } else if (task.contains(inputTypes.DONE.getValue())) {
                    int itemDone = Integer.parseInt(task.substring(5));
                    tasks.get(itemDone - 1).done = true;
                    System.out.println("    Nice! I've marked this task as done:");
                    System.out.println("      " + tasks.get(itemDone - 1).toString());
                } else if (task.equals(inputTypes.LIST.getValue())) {
                    System.out.println("    Here are the tasks in your list:");
                    for (int i = 1; i <= tasks.size(); i++) {
                        System.out.println("    " + i + "." + tasks.get(i - 1).toString());
                    }
                } else if (task.contains(inputTypes.DELETE.getValue())) {
                    int itemDeleted = Integer.parseInt(task.substring(7));
                    Task deletedTask = tasks.remove(itemDeleted - 1);
                    System.out.println("    Noted. I've removed this task:");
                    System.out.println("      " + deletedTask.toString());
                    System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
                } else {
                    throw new Exception("Cannot Understand");
                }
            } catch (Exception e) {
                if (e.getMessage().equals("todoEmpty")) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                } else if (e.getMessage().equals("Cannot Understand")) {
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } finally {
                System.out.println("    ____________________________________________________________");
                task = sc.nextLine();
            }
        }
        System.out.println("    ____________________________________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }
}
