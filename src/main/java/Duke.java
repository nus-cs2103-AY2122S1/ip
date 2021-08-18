import java.util.Scanner;

public class Duke {
    static Scanner sc = new Scanner(System.in);
    static boolean running = true;
    static Task[] tasks = new Task[100];
    static int taskIndex = 0;
    static String defaultErrorMsg = "im sorry I is no understand.";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("hello name is duke");
        System.out.println("how is help today; （´・｀ ）♡");

        while (running) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                System.out.println("okay is bye!!");
                running = false;
            } else if (input.equals("list")) {
                if (taskIndex == 0) {
                    System.out.println("is no tasks today.");
                } else {
                    for (int i = 1; i <= taskIndex; i++) {
                        Task currTask = tasks[i - 1];
                        System.out.println(i + ".[" + currTask.getStatusIcon() + "] "
                                + currTask.getDescription());
                    }
                }
            } else if (input.length() >= 5) {
                if (input.substring(0, 5).equals("done ")) {
                    try {
                        int i = Integer.parseInt(input.substring(5));
                        if (i > taskIndex) {
                            System.out.println("we is dont have that many tasks yet.");
                        } else if (i <= 0) {
                            System.out.println("what kind of number is (||❛︵❛.)");
                        } else {
                            Task task = tasks[i - 1];
                            task.markAsDone();
                            System.out.println("is done!");
                            System.out.println("[" + task.getStatusIcon() + "] " + task.getDescription());
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(defaultErrorMsg);
                    }
                }
            } else {
                if (taskIndex < 100) {
                    Task newTask = new Task(input);
                    tasks[taskIndex] = newTask;
                    System.out.println("added: " + newTask.getDescription());
                    taskIndex++;
                } else {
                    System.out.println("memory is full please is try later.");
                }
            }
        }
    }
}
