import java.util.Scanner;

public class Jared {
    private static Task[] history = new Task[100];
    private static int historyCount = 0;

    private static void add(String input) {
        Task newTask = new Task(input);
        System.out.println("added: " + newTask.toString());
        history[historyCount] = newTask;
        historyCount ++;
    }

    private static void list() {
        String res = "";
        for (int i = 0; i < historyCount; i++) {
            Task currTask = history[i];
            res += String.format("%d.[%s] %s\n", i + 1, currTask.getStatusIcon(), currTask.toString());
        }
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
            if (next.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
            } else if (next.equals("list")) {
                list();
            } else {

                add(next);
            }
        }

    }
}

