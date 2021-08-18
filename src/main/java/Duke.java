import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
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

        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        while(!userInput.toLowerCase().equals(exitString)) {
            System.out.print(line + userInput + line);
            userInput = sc.nextLine();
        }
        System.out.println(endGreeting);
    }
}
