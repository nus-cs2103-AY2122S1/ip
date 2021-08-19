import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.lang.StringBuilder;

public class Duke {
    private static String WELCOME_TEXT = "Hey there I'm Duke!\n" + "How can I help you today?";
    private static String BYE_TEXT = "Bye! Hope to see you again!";

    private static void printMessage(String string) {
        System.out.print("------------------------------------------------\n" + string + "\n"
                + "------------------------------------------------\n\n");
    }

    public static void main(String[] args) {
        printMessage(WELCOME_TEXT);

        Scanner in = new Scanner(System.in);
        List<String> inputList = new ArrayList<>();

        while (true) {
            System.out.print("> ");
            String input = in.nextLine();

            if (input.equals("bye")) {
                printMessage(BYE_TEXT);
                break;
            } else if (input.equals("list")) {
                StringBuilder builder = new StringBuilder();

                for (int i = 0; i < inputList.size(); i++) {
                    String item = inputList.get(i);
                    builder.append(i + 1);
                    builder.append(". ");
                    builder.append(item);
                    if (i < inputList.size() - 1) {
                        builder.append("\n");
                    }
                }

                printMessage(builder.toString());
            } else {
                printMessage(input);
                inputList.add(input);
            }
        }

        in.close();
    }
}
