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
        String res = sc.nextLine();
        while (! res.equals("bye")) {
            // prints the response
            if (res.equals("list")) {
                respond(taskGroup.toString());
            } else {
                respond(taskGroup.add(res));
            }
            res = sc.nextLine();
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
