import java.util.Scanner;

public class Duke {
    static Scanner sc = new Scanner(System.in);
    static boolean running = true;
    static String[] tasks = new String[100];
    static int taskIndex = 0;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("hello name is duke");
        System.out.println("how is help today;");

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
                        System.out.println(i + ". " + tasks[i - 1]);
                    }
                }
            } else {
                if (taskIndex < 100) {
                    tasks[taskIndex] = input;
                    System.out.println("added: " + input);
                    taskIndex++;
                } else {
                    System.out.println("memory is full please is try later.");
                }
            }
        }
    }
}
