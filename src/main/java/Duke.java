import javax.swing.*;
import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("──────────────────────────────────────────");
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can i do for you?");
        System.out.println("──────────────────────────────────────────");

        String input = scanner.nextLine();
        System.out.println("──────────────────────────────────────────");

        while (!Objects.equals(input, "bye")) {
            System.out.println(input);
            System.out.println("──────────────────────────────────────────");
            input = scanner.nextLine();
            System.out.println("──────────────────────────────────────────");
        }
        System.out.println("Bye, hope to see you again soon!");
    }
}
