import java.util.Scanner;
public class Duke {
    private static String name = "Catobot";
    private static String banner = "(=^･ω･^=)(=^･ω･^=)(=^･ω･^=)(=^･ω･^=)";
    private static String greeting = "Hello I am " + name + " (>^ω^<)\n    What can I do for you meow?";
    private static String byeMessage = "Bye meow! I will always wait here meow(>^ω^<)";

    public static void main(String[] args) {
        // creates an object of Scanner
        Scanner sc = new Scanner(System.in);
        respond(greeting);
        TaskList taskGroup = new TaskList();

        // takes input from the keyboard
        String command = sc.next();
        while (!command.equals("bye")) {
            // prints the response
            if (command.equals("list")) {
                respond(taskGroup.display());
            } else if (command.equals("done")) {
                int index = Integer.parseInt(sc.next());
                respond(taskGroup.completeTask(index));
            } else {
                respond(taskGroup.add(new Task(command)));
            }
            command = sc.next();
        }

        respond(byeMessage);
        // closes the scanner
        sc.close();
    }

    private static void respond(String message) {
        String s = String.format("    %s\n    %s\n    %s", banner, message, banner);
        System.out.println(s);
    }
}
