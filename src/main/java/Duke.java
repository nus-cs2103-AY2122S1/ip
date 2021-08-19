import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String input;
        while (true) {
            input = scanner.nextLine();
            System.out.printf("\t");
            if ("bye".equals(input)) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            }
            System.out.println(input);
        }
    }
}
