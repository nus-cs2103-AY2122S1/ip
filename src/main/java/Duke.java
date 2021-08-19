import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        echo(greetMessage());

        Scanner sc = new Scanner(System.in);
        String inputCommand = "";

        do {
            System.out.print("Enter command: \t");
            inputCommand = sc.nextLine().trim();

            switch (inputCommand) {
                case "bye":
                    echo(exitMessage());
                    break;
                default:
                    echo("\t" + inputCommand);
                    break;
            }
        } while (!inputCommand.equals("bye"));
    }

    private static String greetMessage() {
        return "\t" + "Hello! I'm Duke" + System.lineSeparator() +
                "\t" + "What can I do for you?";
    }

    private static String exitMessage() {
        return "\t" + "Bye. Hope to see you again soon!";
    }

    private static void echo(String message) {
        String horizontalLine = "\t" +
                "____________________________________________________________";
        String nextLine = System.lineSeparator();
        String echoMessage = horizontalLine + nextLine +
                message + nextLine + horizontalLine;
        System.out.println(echoMessage);
    }
}
