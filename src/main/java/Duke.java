import java.util.Scanner;

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
        
        while (true) {
            String input = sc.nextLine();
            DukeOperator operator = new DukeOperator(input.trim(), storage);
            try {
                if (!operator.operate()) break;
            } catch (DukeException | IllegalArgumentException e) {
                System.out.println(StringFormat.tabAndFormat(e.getMessage()));
            }
        }
        sc.close();
    }
}
