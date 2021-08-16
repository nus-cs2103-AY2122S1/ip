import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Blue {

    private static final String LOGO = " ____  _                \n"
            + "|  . \\| | _   _   ____  \n"
            + "|____/| || | | | /  _  \\\n"
            + "|  . \\| || |_| ||   ___/\n"
            + "|____/|_||_____| \\_____/\n";
    private static final String GREET_CONTENT = "Hello! I'm Blue\n"
            + "What can I do for you?";
    private static final String EXIT_CONTENT = "Bye. Hope to never see you again!";

    private static final List<String> storedText = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println(LOGO);
        greet();
        String command = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            command = scanner.nextLine();
            boolean shouldContinue = handle(command);
            if (!shouldContinue)
                break;
        }
        scanner.close();
    }

    private static void greet() {
        speak(GREET_CONTENT);
    }

    private static boolean handle(String command) {
        if (command.equals(Command.EXIT)) {
            speak(EXIT_CONTENT);
            return false;
        }
        if (command.equals(Command.LIST))
            listText();
        else
            storeAndDisplayText(command);
        return true;
    }

    private static void storeAndDisplayText(String text) {
        storedText.add(text);
        String content = "added: " + text;
        speak(content);
    }

    private static void listText() {
        String[] lines = new String[storedText.size()];
        for (int i = 0; i < storedText.size(); i++) {
            String intString = Integer.toString(i + 1);
            String text = storedText.get(i);
            lines[i] = intString + ". " + text;
        }
        String content = String.join("\n", lines);
        speak(content);
    }

    private static void speak(String content) {
        String line = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
        System.out.println(line);
        System.out.println(content);
        System.out.println(line);
    }
}
