import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("How can I help you?");
        System.out.println("_______________________");
        Scanner commands = new Scanner(System.in);
        String toEcho = commands.nextLine();
        while (!toEcho.equals("bye")) {
            String toPrint = toEcho;
            System.out.println("_______________________");
            System.out.println(toPrint);
            System.out.println("_______________________");
            toEcho = commands.nextLine();
        }
        System.out.println("_______________________");
        System.out.println("See you! Have a nice day!");
        System.out.println("_______________________");
    }
}
