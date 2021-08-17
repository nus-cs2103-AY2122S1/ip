import java.util.Scanner;

public class Jared {
    private static Task[] history = new Task[100];
    private static int historyCount = 0;

    private static void add(String command, String next) {
        Task newTask;
        if (command.equals("todo")) {
            String desc = next.split(" ",2)[1];
            newTask = new Todo(desc);
        } else if (command.equals("deadline")) {
            String body = next.split(" ",2)[1];
            String desc = body.split("/by",2)[0];
            String date = body.split("/by",2)[1];
            newTask = new Deadline(desc, date);
        } else if (command.equals("event")) {
            String body = next.split(" ", 2)[1];
            String desc = body.split("/at",2)[0];
            String date = body.split("/at",2)[1];
            newTask = new Event(desc, date);
        } else {
            newTask = new Task(next);
        }
        history[historyCount] = newTask;
        historyCount ++;
        System.out.println(String.format("Got it. I've added this task:\n" +
                        "%s\nNow you have %d tasks in the list.",
                newTask.toString(), historyCount)
        );
    }

    private static void list() {
        String res = "Here are the tasks in your list:\n";
        for (int i = 0; i < historyCount; i++) {
            Task currTask = history[i];
            res += String.format("%d. %s\n", i + 1, currTask.toString());
        }
        System.out.println(res);
    }

    private static void done(String next) {
        int taskNum = Integer.valueOf(next.split(" ", 2)[1]);
        int index = taskNum - 1;
        Task currTask = history[index];
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
        while (true) {
            String next = scan.nextLine();
            String[] inputArr = next.split(" ",2);
            String command = inputArr[0];

            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
            } else if (command.equals("list")) {
                list();
            } else if (command.equals("done")) {
                done(next);
            } else {
                add(command, next);
            }
        }

    }
}

