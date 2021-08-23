import java.io.File;
import java.io.FileNotFoundException;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static List<Task> tasks = new ArrayList<>();
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

    private static void todoEvent (String task) throws Exception {
        if (task.length() <= 5) {
            throw new Exception("todoEmpty");
        }
        String taskName = task.substring(5);
        tasks.add(new Todo(taskName));
        System.out.println("     Got it. I've added this task:");
        System.out.println("      " + tasks.get(tasks.size() - 1).toString());
        System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void deadlineEvent (String task) throws Exception {
        int position = task.indexOf('/');
        String taskName = task.substring(9, position - 1);
        String deadlineTime = task.substring(position + 4);
        tasks.add(new Deadline(taskName, deadlineTime));
        System.out.println("     Got it. I've added this task:");
        System.out.println("      " + tasks.get(tasks.size() - 1).toString());
        System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void event (String task) throws Exception {
        int position = task.indexOf('/');
        String taskName = task.substring(6, position - 1);
        String eventTime = task.substring(position + 4);
        tasks.add(new Event(taskName, eventTime));
        System.out.println("     Got it. I've added this task:");
        System.out.println("      " + tasks.get(tasks.size() - 1).toString());
        System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void setEventDone (String task) throws Exception {
        int itemDone = Integer.parseInt(task.substring(5));
        tasks.get(itemDone - 1).done = true;
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("      " + tasks.get(itemDone - 1).toString());
    }

    private static void showList () throws Exception {
        System.out.println("    Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println("    " + i + "." + tasks.get(i - 1).toString());
        }
    }

    private static void deleteEvent (String task) throws Exception {
        int itemDeleted = Integer.parseInt(task.substring(7));
        Task deletedTask = tasks.remove(itemDeleted - 1);
        System.out.println("    Noted. I've removed this task:");
        System.out.println("      " + deletedTask.toString());
        System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
    }


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String dividingLine = "    ____________________________________________________________";
        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        System.out.println(dividingLine);
        System.out.println("    Hello! I'm Irving.");
        System.out.println("    What can I do for you?");
        System.out.println(dividingLine);
        String task = sc.nextLine();
        while (!task.equals(inputTypes.BYE.getValue())) {
            try {
                System.out.println(dividingLine);
                if (task.contains(inputTypes.TODO.getValue())) {
                    todoEvent(task);
                } else if (task.contains(inputTypes.DEADLINE.getValue())) {
                    deadlineEvent(task);
                } else if (task.contains(inputTypes.EVENT.getValue())) {
                    event(task);
                } else if (task.contains(inputTypes.DONE.getValue())) {
                    setEventDone(task);
                } else if (task.equals(inputTypes.LIST.getValue())) {
                    showList();
                } else if (task.contains(inputTypes.DELETE.getValue())) {
                    deleteEvent(task);
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
                System.out.println(dividingLine);
                task = sc.nextLine();
            }
        }
        System.out.println(dividingLine);
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println(dividingLine);
    }
}
