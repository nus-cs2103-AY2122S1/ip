import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Duke {
    private static void reply(String msg) {
        String indentedMsg = Arrays.asList(msg.split("\n")).stream()
                                   .map(s -> String.format("\t%s\n", s))
                                   .collect(Collectors.joining(""));
        System.out.printf(
                        "\t____________________________________\n" +
                        "%s" +
                        "\t____________________________________\n", indentedMsg);
    }

    private static void greet() {
        reply("Hello! I'am Duke\n" +
                "What can I do for you?");
    }

    private static String getUserInput() {
        return new Scanner(System.in).nextLine();
    }

    private static void quit() {
        reply("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        greet();
        boolean shouldExit = false;
        while(!shouldExit) {
            String userInput = getUserInput();
            switch (userInput) {
                case "bye":
                    quit();
                    shouldExit = true;
                    break;
                default:
                    // basically echo
                    reply(userInput);
            }
        }
    }
}
