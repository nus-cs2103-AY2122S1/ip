import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Im Duke\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String border = "____________________________________________________________";
        while (!input.equals("bye")) {
            System.out.println(border);
            System.out.println(input);
            System.out.println(border);
            input = sc.nextLine();
        }
        System.out.println(border);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(border);
    }
}
