import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String message = "Hello! I'm Duke \n" + "What can I do for you? \n";
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while(!input.equals("bye")) {
            System.out.println("\t" + input);
            input = scanner.nextLine();
        }
        String ending = "Bye. Hope to see you again soon!";
        System.out.println("\t" + ending);
    }
}
