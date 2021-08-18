import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    private static final String[] list = new String[100];
    private static int listNumber = 0;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "What can I do for you?");
        Scanner s = new Scanner(System.in);
        String command = s.nextLine();
        while(!command.equals("bye")) {
            switch(command) {
                case "list":
                    for (int i = 0; i < listNumber; i++) {
                        int num = i + 1;
                        if (list[i] != null) {
                            System.out.println(num + ". " + list[i]);
                        }
                    }
                    command = s.nextLine();
                    break;
                default:
                    System.out.println("added: " + command);
                    list[listNumber]= command;
                    listNumber += 1;
                    command = s.nextLine();
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
