import java.util.Scanner;
public class Duke {
    private static String name = "Catobot";
    private static String banner = "(=^^=)(=^^=)(=^^=)(=^^=)";
    private static String greeting = "Hello I am " + name + " (>^^<)\n    What can I do for you meow?";
    private static String byeMessage = "Bye meow! I will always wait here meow(>^^<)";

    public static void main(String[] args) {
        // creates an object of Scanner
        Scanner sc = new Scanner(System.in);
        respond(greeting);
        TaskList taskGroup = new TaskList();

        // takes input from the keyboard
        String request = sc.nextLine();
        String command = request.split(" ")[0];
        while (!command.equals("bye")) {
            // prints the response
            if (command.equals("list")) {
                respond(taskGroup.display());

            } else if (command.equals("done")) {
                int index = Integer.parseInt(request.substring(request.indexOf(" ") + 1));
                respond(taskGroup.completeTask(index));

            } else if (command.equals("todo")) {
                String description = request.substring(request.indexOf(" ") + 1);
                respond(taskGroup.add(new Todo(description)));

            } else if (command.equals("deadline")) {
                String description = request.substring(request.indexOf(" ") + 1, request.indexOf("/by") - 1);
                String date = request.substring(request.indexOf("/by") + 4);
                respond(taskGroup.add(new Deadline(description, date)));

            } else if (command.equals("event")) {
                String description = request.substring(request.indexOf(" ") + 1, request.indexOf("/at") - 1);
                String date = request.substring(request.indexOf("/at") + 4);
                respond(taskGroup.add(new Event(description, date)));

            } else {
                respond(taskGroup.add(new Task(command)));
            }
            request = sc.nextLine();
            command = request.split(" ")[0];
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
