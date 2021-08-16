import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner scanner = new Scanner(System.in);
        String toRepeat = scanner.next();

        while (!toRepeat.equals("bye")) {
            System.out.println("\t" + toRepeat + "\n");
            toRepeat = scanner.next();
        }

        scanner.close();
    }
}
