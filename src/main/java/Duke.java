import java.lang.reflect.Array;
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
        String[] arr = new String[100];
        int len = 0;
        while (true) {
            input = scanner.nextLine();
            switch (input) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    return;
                case "list":
                    for (int i = 0; i < len; i++) {
                        System.out.printf("%d. %s\n", i + 1, arr[i]);
                    }
                    break;
                default:
                    arr[len++] = input;
                    System.out.println("added: " + input);
            }
        }
    }
}
