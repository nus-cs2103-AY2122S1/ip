import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private final static ArrayList<Task> list = new ArrayList<>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        reply("Hello i is Duke\nWhat u want?");
        manageInput();
    }

    private static void manageInput() {
        Scanner obj = new Scanner(System.in);
        String input = obj.nextLine();
        if (input.equals("bye")) {
            exit();
        } else if (input.equals("list")) {
            displayList();
        } else if (input.contains("done")) {
            done(input);
        } else if (input.contains("todo")) {
            todo(input);
        } else if (input.contains("deadline")) {
            deadline(input);
        } else if (input.contains("event")) {
            event(input);
        } else {
            reply("Can type properly pls?");
        }
    }

    private static void reply(String content) {
        System.out.println("________________________________");
        System.out.println(content);
        System.out.println("________________________________");
    }

    private static void exit() {
        reply("i zao first");
    }

    private static void echo(String input) {
        reply(input);
        manageInput();
    }

    private static void add(Task task) {
        list.add(task);
        reply("one more thing: " + task.toString() + "\nNow you got " + list.size() + " thing(s). sian");
        manageInput();
    }

    private static void displayList() {
        System.out.println("________________________________");
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            System.out.println((i + 1) + "." + task.toString());
        }
        System.out.println("________________________________");
        manageInput();
    }

    private static void done(String input) {
        int taskNumber = Integer.parseInt(input.substring(5));
        Task currTask = list.get(taskNumber - 1);
        currTask.markAsDone();
        reply("noice this thing done:\n" + currTask);
        manageInput();
    }

    private static void todo(String input) {
        Todo todo = new Todo(input.substring(5));
        add(todo);
        manageInput();
    }

    private static void deadline(String input) {
        int split = input.indexOf("/");
        if (split == -1) {
            reply("this one by when ah? can do it liddis or not: 'deadline task /by when'");
        } else {
            Deadline deadline = new Deadline(input.substring(9, split - 1), input.substring(split + 3));
            add(deadline);
            manageInput();
        }
    }

    private static void event(String input) {
        int split = input.indexOf("/");
        if (split == -1) {
            reply("this one when ah? can do it liddis or not: 'event task /at when'");
        } else {
            Event event = new Event(input.substring(6, split - 1), input.substring(split + 3));
            add(event);
            manageInput();
        }
    }
}
