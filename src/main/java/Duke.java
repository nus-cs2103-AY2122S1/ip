import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String[] startMessage = {" ____        _        ", 
                    "|  _ \\ _   _| | _____ ",
                    "| | | | | | | |/ / _ \\",
                    "| |_| | |_| |   <  __/",
                    "|____/ \\__,_|_|\\_\\___|",
                    "Hello! I'm Duke",
                    "What can I do for you?"};


        System.out.println(StringFormat.formatString(startMessage));

        String[] storage = new String[100]; // Storage for user input
        int index = 0; // index for current item in the storage

        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            String input = sc.next();

            // if it is "bye", we exit the loop
            if (input.equals("bye")) {
                System.out.println(StringFormat.formatString("Bye. Hope to see you again soon!\n"));
                break;
                
            // if it is "list", we list the stored inputs
            } else if (input.equals("list")) {
                System.out.println(StringFormat.formatString(storage));

            // if it is not "list" or "bye", we store the user input and notify them.
            } else {
                storage[index] = String.valueOf(index++) + ". " + input;
                System.out.println(StringFormat.formatString("Added: " + input));
            }
        }
        sc.close();
    }
}
