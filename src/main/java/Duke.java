import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from Duke\n" + logo);
        System.out.println("\t____________________________");
        System.out.println("\tHello!, I'm Duke\n\tWhat can I do for you?");
        System.out.println("\t____________________________");
        run();
    }

    static void run() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            System.out.println("\t____________________________");
            if (input.toLowerCase().equals("bye") || input.toLowerCase().equals("exit")) {
                System.out.println("\tBye. Hope to see you again soon!");
                System.out.println("\t____________________________");
                break;
            }
            System.out.println("\t" + input);
            System.out.println("\t____________________________");
        }
    }
}
