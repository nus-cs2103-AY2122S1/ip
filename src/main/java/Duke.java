import java.util.Scanner;

public class Duke {
    final private static String LINE = "     ____________________________________________________________\n";
    final private static String INDENT = "      ";

    private static void formatOutput(String inp) {
        System.out.println(LINE + INDENT + inp + '\n' +LINE);
    }

    private static void getInput() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                formatOutput("Bye. Hope to see you again soon!");
                break;
            }
            formatOutput(input);
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String output =  "Hello! Welcome to\n" + logo + "\nHow may i help you?\n";
        System.out.println(LINE.trim());
        output.lines().forEach(op -> System.out.println("      " + op));
        System.out.println(LINE.trim());
        getInput();
    }
}
