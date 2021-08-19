import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // TODO: Add enum CommandKeyword "done" "deadline" "event"
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String line = "\n------------------------------------------\n";
        String startGreeting = line + "Hello! I'm Duke\n"
                + "What can I do for you?" + line;
        String endGreeting = line + "Bye. Hope to see you again soon!" + line;
        String exitString = "bye";
        System.out.println("Hello from\n" + logo + startGreeting);

        TaskManager tm = new TaskManager();
        Parser p = new Parser();
        Scanner sc = new Scanner(System.in);


        String userInput = sc.nextLine();
        while (!userInput.trim().toLowerCase().equals(exitString)) {
            try {
                Command command = p.getCommand(userInput);
                String output = command.execute(tm);
                System.out.print(line + output + line);
            } catch (DukeException e) {
                System.out.println(line + e.getMessage() + line);
            }
            userInput = sc.nextLine();
        }
        System.out.println(endGreeting);
    }
}
