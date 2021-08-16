import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {
    public static void main(String[] args) {
        // Starting Message
        String[] startMessage = {" ____        _        ", 
                    "|  _ \\ _   _| | _____ ",
                    "| | | | | | | |/ / _ \\",
                    "| |_| | |_| |   <  __/",
                    "|____/ \\__,_|_|\\_\\___|",
                    "Hello! I'm Duke",
                    "What can I do for you?"};
        System.out.println(StringFormat.formatString(startMessage));

        // Storage for tasks
        TaskStorage storage = new TaskStorage();

        // Taking in user input
        Scanner sc = new Scanner(System.in);
        
        // While there's user input
        while(sc.hasNext()) {
            String input = sc.nextLine();

            // Regex Matcher to check the input to mark items as done
            Pattern p = Pattern.compile("done\\s+\\d+");
            Matcher m = p.matcher(input);


            // if it is "bye", we exit the loop
            if (input.equals("bye")) {
                System.out.println(StringFormat.formatString("Bye. Hope to see you again soon!\n"));
                break;
                
            // if it is "list", we list the stored inputs
            } else if (input.equals("list")) {
                System.out.println(
                    StringFormat.formatString(
                        StringFormat.tabAllNewline(
                            storage.toString()
                        )
                    )
                );

            // if input == "done" and the next input is a number, we are marking a task as done.
            } else if (m.find()) {
                String[] result = input.split(" ");
                int ind = Integer.valueOf(result[1]) - 1;

                System.out.println(
                    StringFormat.formatString(
                        StringFormat.tabAllNewline(
                            storage.markDone(ind)
                        )
                    )
                );

            // if it is not "list" or "bye", we store the user input and notify them.
            } else {
                System.out.println(
                    StringFormat.formatString(
                        storage.add(new Task(input)
                        )
                    ) 
                );
            }
        }
        sc.close();
    }
}
