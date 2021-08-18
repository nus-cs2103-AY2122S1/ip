import javax.swing.*;
import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] list = new String[100];
        int pointer = 0;

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

        while (!Objects.equals(input, "bye")) {
            switch(input) {
                case "list":
                    System.out.println("──────────────────────────────────────────");
                    for (int i = 0; i < pointer; i++) {
                        String cur = list[i];
                        int label = i + 1;
                        System.out.println(label + ". " + cur);
                    }
                    System.out.println("──────────────────────────────────────────");
                    input = scanner.nextLine();
                    break;

                default:
                    System.out.println("──────────────────────────────────────────");
                    list[pointer] = input;
                    pointer++;
                    System.out.println("added: " + input);
                    System.out.println("──────────────────────────────────────────");
                    input = scanner.nextLine();
                    break;
            }
        }
        System.out.println("──────────────────────────────────────────");
        System.out.println("Bye, hope to see you again soon!");
    }
}
