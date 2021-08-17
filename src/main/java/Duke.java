import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String tab = "      ";
        String line = "------------------------------------------------------------";
        System.out.println(line);
        System.out.println("Hello. My name is Necro.");
        System.out.println("What can I do for you on this horrible day?");
        System.out.println(line);
        System.out.println("");

        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                System.out.println(tab + line);
                System.out.println(tab + " " + "Farewell, may we never meet again.");
                System.out.println(tab + line);
                break;
            } else {
                System.out.println(tab + line);
                System.out.println(tab + " " + input);
                System.out.println(tab + line);
            }
        }
    }
}
