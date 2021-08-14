import java.util.Scanner;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class Duke {
    static ArrayList<String> tasks = new ArrayList<>();

    public static void main(String[] args) {
        greet();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                print("Bye. Hope to see you again soon!");
                return;
            } else if (input.equals("list")) {
                print(IntStream
                        .range(0, tasks.size())
                        .mapToObj(x -> (x + 1) + ". " + tasks.get(x))
                        .toArray(String[]::new));
            } else {
                tasks.add(input);
                print("added: " + input);
            }
        }
    }

    static void greet() {
        print("Hello! I'm Duke", "What can I do for you?");
    }

    static void print(String... lines) {
        printSeparator();
        for (String line : lines)
            println(line);
        printSeparator();
    }

    static void printSeparator() {
        System.out.println("\t____________________________________");
    }

    static void println(String out) {
        System.out.println("\t " + out);
    }

}
