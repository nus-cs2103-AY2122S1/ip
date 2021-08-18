import java.util.Scanner;

public class Duke {
    static Scanner sc = new Scanner(System.in);
    static boolean running = true;

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
            } else {
                System.out.println(input);
            }
        }
    }
}
