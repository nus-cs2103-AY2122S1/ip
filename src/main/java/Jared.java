import java.util.ArrayList;
import java.util.Scanner;

public class Jared {
    private static ArrayList<Task> history = new ArrayList<Task>();

    private static void add(String command, String next) throws DukeException {
        Task newTask;
        String desc;
        if (command.equals("todo")) {
            try {
                desc = next.split(" ", 2)[1];
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("OOPS!!! The description of a todo cannot be empty.\n" +
                        "Please enter a description of your task:");
                Scanner scan = new Scanner(System.in);
                desc = scan.nextLine();
            }
            newTask = new Todo(desc);
        } else if (command.equals("deadline")) {
            String body = next.split(" ",2)[1];
            desc = body.split("/by",2)[0];
            String date = body.split("/by",2)[1];
            newTask = new Deadline(desc, date);
        } else if (command.equals("event")) {
            String body = next.split(" ", 2)[1];
            desc = body.split("/at",2)[0];
            String date = body.split("/at",2)[1];
            newTask = new Event(desc, date);
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        history.add(newTask);
        System.out.println(String.format("Got it. I've added this task:\n" +
                        "%s\nNow you have %d tasks in the list.",
                newTask.toString(), history.size())
        );
    }

    private static void list() {
        String res = "Here are the tasks in your list:\n";
        for (int i = 0; i < history.size(); i++) {
            Task currTask = history.get(i);
            res += String.format("%d. %s\n", i + 1, currTask.toString());
        }
        System.out.println(res);
    }

    private static void done(String next) {
        int taskNum;
        try {
            taskNum = Integer.valueOf(next.split(" ", 2)[1]);
            Task currTask = history.get(taskNum-1);
        } catch (Exception e) {
            System.out.println("Invalid. Please enter the index of task you have completed:");
            Scanner scan = new Scanner(System.in);
            taskNum = Integer.valueOf(scan.nextLine());
        }
        int index = taskNum - 1;
        Task currTask = history.get(index);
        currTask.markDone();
        String res = String.format("Nice! I've marked this task as done:\n%s",
                currTask.toString());
        System.out.println(res);
    }

    public static void main(String[] args) {
        String intro = "____________________________________________________________\n" +
                "Hello! I'm Jared\n" +
                "What can I do for you?\n" +
                "____________________________________________________________";
        Scanner scan = new Scanner(System.in);

        System.out.println(intro);
        while (scan.hasNextLine()) {
            String next = scan.nextLine();
            String[] inputArr = next.split(" ",2);
            String command = inputArr[0];

            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.exit(0);
                scan.close();
            } else if (command.equals("list")) {
                list();
            } else if (command.equals("done")) {
                done(next);
            } else {
                try {
                    add(command, next);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

    }
}

