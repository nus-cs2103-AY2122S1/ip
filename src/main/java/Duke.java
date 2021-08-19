import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        List<String> commandsList = new ArrayList<String>();

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
                case "list":
                    echo(displayCommandsList(commandsList));
                    break;
                default:
                    commandsList.add(inputCommand);
                    echo(addedCommandMessage(inputCommand));
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


    private static String addedCommandMessage(String inputCommand) {
        return "\t" + "added: " + inputCommand;
    }

    private static String displayCommandsList(List<String> commandsList) {
        String result = "";

        for (int i = 0; i < commandsList.size(); i++) {
            if (i != 0) {
                result += System.lineSeparator();
            }

            result += "\t" + (i + 1) + ". " + commandsList.get(i);
        }

        return result;
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
