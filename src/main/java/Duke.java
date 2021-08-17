import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        monitor();
    }

    private static void monitor() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            renderOutput(input);
            if (input.strip().equalsIgnoreCase("bye")) {
                break;
            }
        }
    }

    private static void renderOutput(String output) {
        System.out.println("    ____________________________________________________________");
        output.lines().map(x -> "     " + x).forEach(System.out::println);
        System.out.println("    ____________________________________________________________");
    }
}
