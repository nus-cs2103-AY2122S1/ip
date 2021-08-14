import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        greet();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                print(bye());
                return;
            } else {
                print(input);
            }
        }
    }

    static void greet() {
        print("Hello! I'm Duke", "What can I do for you?");
    }

    static String[] bye() {
        return new String[]{"Bye. Hope to see you again soon!"};
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
