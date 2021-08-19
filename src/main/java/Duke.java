import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    private static String welcomeText = "Hey there I'm Duke!\n"
            + "How can I help you today?\n";
    private static String byeText = "Bye! Hope to see you again!\n";


    public static void main(String[] args) {
        System.out.print(welcomeText);

        Scanner in = new Scanner(System.in);
        List<String> inputList = new ArrayList<>();

        while (true) {
            String input = in.nextLine();

            if (input.equals("bye")) {
                System.out.print(byeText);
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < inputList.size(); i++) {
                    String item = inputList.get(i);
                    System.out.println((i + 1) + ". " + item);
                }
            } else {
                System.out.println(input);
                inputList.add(input);
            }
        }
    }
}
