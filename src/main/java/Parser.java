import java.util.Scanner;

public class Parser {
    public static String[] parse(String userInput) {
        Scanner sc = new Scanner(userInput);
        String command = sc.next();
        String input = userInput.replace(command, "").strip();
        return new String[]{command, input};
    }
}
