import java.util.Scanner;

public class Duke {
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] stringArray = new String[100];
        int listIndex = 0;

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo + "\nWhat can I do for you today?");

        while(true) {
            String input = scan.next();

            if (input.equals("list")) {
                for (int i = 0; i < 100; i++) {
                    if (stringArray[i] == null) {
                        break;
                    }
                    int listNumber = i + 1;
                    System.out.println(listNumber + ". " + stringArray[i]);
                }
                continue;
            }

            if (input.equals("bye")) {
                break;
            }

            System.out.println("Added: " + input);
            stringArray[listIndex] = input;
            listIndex += 1;
        }

        scan.close();
        System.out.println("See you again next time!");
    }
}
