import java.util.Scanner;
import java.util.function.UnaryOperator;

public class Duke {
    private static String introString = "Hey there! I'm Good Duke. How many I help you today?";
    private static String outroString = "That was an excellent chat - I look forward to seeing you again soon!";
    private static UnaryOperator<String> addedString = item ->
            String.format("Alright, I've added \"%s\" to my list :)", item);
    private static Scanner sc = new Scanner(System.in);
    private static String[] taskList = new String[100];
    private static int taskIndex = 0;

    public static void printList() {
        for (int i = 0; i < taskIndex; i++) {
            String output = String.format("%d. %s", i + 1, taskList[i]);
            System.out.println(output);
        }
    }

    public static void main(String[] args) {
        System.out.println(introString);
        while (true) {
            String userEntry = sc.nextLine();
            if (userEntry.equals("bye")) {
                System.out.println(outroString);
                break;
            } else if (userEntry.equals("list")) {
                printList();

            } else {
                taskList[taskIndex] = userEntry;
                taskIndex++;
                System.out.println(addedString.apply(userEntry));
            }
        }
    }
}
