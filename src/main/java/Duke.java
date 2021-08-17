import java.util.*;

public class Duke {
    private static final String SEPARATOR = "__________________________________________\n";
    private static final String GREETING = "Hello! I'm Talky McTalkFace\n"
            + "What can I do for you?\n";
    private static final String EXIT = "Bye. Hope to see you again soon!\n";
    private static final String PROMPT = "What would you like to do?";

    public static void main(String[] args) {
        String ExitCommand = "bye";
        String ListCommand = "list";
        String prefix = "added: ";
        String input = "";

        List<String> taskList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        System.out.print(SEPARATOR + GREETING + SEPARATOR);

        while (!input.equals(ExitCommand)) {
            System.out.println(PROMPT);
            input = sc.nextLine();
            if (input.equals(ExitCommand)) {
                System.out.print(SEPARATOR + EXIT + SEPARATOR);
            } else if (input.equals(ListCommand)) {
                System.out.print(SEPARATOR);
                for (int i = 1; i <= taskList.size(); i++) {
                    System.out.println(i + ". " + taskList.get(i - 1));
                }
                System.out.print(SEPARATOR);
            } else {
                taskList.add(input);
                System.out.print(SEPARATOR + prefix + input + "\n" + SEPARATOR);
            }
        }

    }
}
